package br.com.projeto.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

@RestController
public class Controle {

    @Autowired
    // vai chamar os metodos (cadastrar, excluir , atualizar e adc) (nao vamos mais precisar intanciar objetos)
    private Repositorio acao; 

    @Autowired
    private Servico servico;
    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    // @PostMapping("/api")
    // public Pessoa cadastrar(@RequestBody Pessoa obj){
    //     return acao.save(obj);
    // }     INATIVADO PELA FUNÇÃO ACIMA

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }


    // @GetMapping("/api")
    // public List<Pessoa> selecionar(){
    //     return acao.findAll();
    // }     INATIVADO PELA FUNÇAO ACIMA

    @GetMapping("/api/{codigo}")//gerando status correto
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){// @PathVariable para avisar que a variaval codigo é int
        return servico.selecionarPeloCodigo(codigo);
    }


    // @GetMapping("/api/{codigo}")//passando parametro de como vai ficar a url
    // public Pessoa selecionarPeloCodigo(@PathVariable int codigo){// @PathVariable para avisar que a variaval codigo é int
    //     return acao.findByCodigo(codigo);
    // } INATIVADO PELO CODIGO ACIMA


    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){//passar um obj completo
        return servico.editar(obj);//save tem 2 fuções, criar em alterar
    }

    //fazendo alteraçoes de dados
    // @PutMapping("/api")
    // public Pessoa editar(@RequestBody Pessoa obj){//passar um obj completo
    //     return acao.save(obj);//save tem 2 fuções, criar em alterar
    // } INATIVADO PELO CODIGO ACIMA

    //removendo
    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remove(@PathVariable int codigo){// codigo vindo pelo PATH
        return servico.remover(codigo);// vai remover pelo codigo
    }


    
    //removendo
    // @DeleteMapping("/api/{codigo}")
    // public void remove(@PathVariable int codigo){
        //tem que ter todos os dados
        // Pessoa obj = selecionarPeloCodigo(codigo);
        // acao.delete(obj);
    // } INATIVADO PELO CODIGO ACIMA


    //ROTA PARA NOVOS DADOS DOS CLIENTES
    @PostMapping("/cliente")
    public void clientes(@Valid @RequestBody Cliente obj){

    }

    //CONTADOR de total de informações
    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    //função para reordenar
    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }
    // funçao para reordenar de foram contraria
    @GetMapping("/api/ordenarNomes1")
    public List<Pessoa> ordenarNomes1(){
        return acao.findByOrderByNomeDesc();
    }

    //funçao para retornar somente as pessoas que quero
    @GetMapping("api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("BATATINHA FRITA 123");//AQUI FILTRA PELO NOME
        }

    @GetMapping("api/ordenarIdade")
    public List<Pessoa> ordenarIdades(){
        return acao.findByOrderByIdade();//ordenado pela idade
    }

    //metodo para verificação de termo
    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("fe");//letrais M ou m nao interfere. termo é o que vamos verificar
    }
    //    startif e endif
    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("b");
    }
    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("a");
    }

    //chamando a função de soma de idades
    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    //funçao de maior ou igual
    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(22);
    }

    //customizar o status das requisições
    @GetMapping("/status")
    public ResponseEntity<?> status(){//<?> obj que nao deixo explicito
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    
    @GetMapping("/")
    public String mensagem(){
        return "Hello World!!";
    }
    @GetMapping("/boasVindas")
    public String boasVidas(){
        return "Seja bem vindo(a)!";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVidas(@PathVariable String nome){
        return "Seja bem vindo(a) " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }
    
}
