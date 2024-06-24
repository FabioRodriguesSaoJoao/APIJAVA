package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Pessoa;

@Repository // CrudRepository para efetuar açoes SQL (passar 2 tipos de informaçoes : modelo do repositorio e o tipo de dados)
public interface Repositorio extends CrudRepository <Pessoa, Integer>{
    //queremos retornar a lista
    List<Pessoa> findAll();
    Pessoa findByCodigo (int codigo); //pega os dados pelo codigo(ID)
    //List<Pessoa> findByCodigo (int codigo); //pega os dados DUPLICADOS

    //ordenar as informaçoes
    List<Pessoa> findByOrderByNome();
    // List<Pessoa> findByOrderByNomeAsc();// ASC ORDENA LETRAS DE A-Z OU NUMEROS DO MENOR PARA O MAIOR
    List<Pessoa> findByOrderByNomeDesc();// desc ordena de foram contraria

    // retornar somente as pessoas que quero
    List<Pessoa> findByNomeOrderByIdadeDesc(String Nome);
    List<Pessoa> findByOrderByIdade();

    //funçao para verificação se existe algo que quero no nome
    List<Pessoa> findByNomeContaining(String termo);

    //start if e and if
    List<Pessoa> findByNomeStartsWith(String termo);
    List<Pessoa> findByNomeEndsWith(String termo);
    
    //criar meu proprio SQL
    @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)//funçao de soma de idades (aqui pode receber todas as funçoes do sql)
    int somaIdades();

    //como manipular parametros no query
    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);//vou passar a idade e ela vai ser o parametro onde ira me retornar as idades iguais ou maiores

    //ajustando status de pesquisa no banco de dados
    int countByCodigo(int codigo);//conta quantos registros existe daquele codigo
    
    
}
