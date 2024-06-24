package br.com.projeto.api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //especifica a criação da tabela
@Table(name = "pessoas") //muda o nome da tabela, pois ela é criada com o nome da classe
// criando modelo


public class Pessoa {

    // Atributos
    @Id //anotation para a chave primaria (cria a primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //inforção auto incrementada (gera o codigo)
    private int codigo;
    private String nome;
    private int idade;
    
    //get e set
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
