package br.com.projeto.api.servico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service//vamos efetuar cadastro como : cadastrar , alterar, deletar,selecionar com boas praticas de validação
public class Servico {
    
    @Autowired
    private Mensagem mensagem;// vamos dar feedback para o usuario

    // metodo de cadastro com validação dos dados
    @Autowired
    private Repositorio acao;//importando repositorio
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if (obj.getNome().equals("")){//se o obj for vazio
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if (obj.getIdade()< 0){// mensagem e verificação de idade
            mensagem.setMensagem("Infome uma idade válida");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            //criando cadastro se tudo estiver ok
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }
    //Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }
    //Método para selecionar pessoas atraves do codigo
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if (acao.countByCodigo(codigo)== 0){
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");// SE NAO ENCONTRAR
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);// SE ENCONTRAR
        }
    }
    // Método para editar dados
    public ResponseEntity<?> editar(Pessoa obj){
        if (acao.countByCodigo(obj.getCodigo())==0){// QUANDO O CODIGO NAO EXISTIR
            mensagem.setMensagem("O codigo informado não existe!");
            return new ResponseEntity<>(mensagem,HttpStatus.NOT_FOUND);

        }else if (obj.getNome().equals("")){// QUANDO NAO INFORMAM NOME
            mensagem.setMensagem("É necessário informar um nome!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else if (obj.getIdade()<0){// PARA IDADE INVALIDA
            mensagem.setMensagem("Informe uma idade válida!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{// PARA QUANDO TODOS OS NOVOS DADOS ESTIVEREM SENDO ADC CORRETAMENTE
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }
    //metodo para remover registro
    public ResponseEntity<?> remover(int codigo){

        if(acao.countByCodigo(codigo)==0){// contando quantos registros existem
            mensagem.setMensagem("O codigo informando não existe!");
            return new ResponseEntity<>(mensagem,HttpStatus.NOT_FOUND);
        }else{
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso!");
            return new ResponseEntity<>(mensagem,HttpStatus.OK);
        }
    }
}
