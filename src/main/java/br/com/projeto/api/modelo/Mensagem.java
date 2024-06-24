package br.com.projeto.api.modelo;

import org.springframework.stereotype.Component;

@Component //vai varrer a aplicação quando estiver sendo executada
public class Mensagem {
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
