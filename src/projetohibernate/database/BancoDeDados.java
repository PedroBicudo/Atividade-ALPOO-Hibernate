/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database;

import projetohibernate.database.dao.BancoDeDadosDao;
import projetohibernate.database.dao.LivroDao;
import projetohibernate.database.dao.AutorDao;
import projetohibernate.database.dao.EditoraDao;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import projetohibernate.database.manipulador.ManipuladorDeAutor;
import projetohibernate.database.manipulador.ManipuladorDeEditora;
import projetohibernate.database.manipulador.ManipuladorDeLivro;

/**
 *
 * @author pedroh
 */
public class BancoDeDados implements BancoDeDadosDao {
    
    private static final String NAME_PERSISTENCE_PU = "ConfiguracaoProjetoHibernatePU";
    private static EntityManagerFactory entityManagerFactory;
    private AutorDao manipuladorDeAutor;
    private EditoraDao manipuladorDeEditora;
    private LivroDao manipuladorDeLivro;

    public BancoDeDados() {
        gerarInstanciaEntityManagerFactory();
        manipuladorDeAutor = new ManipuladorDeAutor(entityManagerFactory);
        manipuladorDeEditora = new ManipuladorDeEditora(entityManagerFactory);
        manipuladorDeLivro = new ManipuladorDeLivro(entityManagerFactory);
    }
    
    private void gerarInstanciaEntityManagerFactory() {
        try {
            if (!isEntityManagerFactoryJaCriada()) {
                entityManagerFactory = Persistence.createEntityManagerFactory(NAME_PERSISTENCE_PU);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private boolean isEntityManagerFactoryJaCriada() {
        return entityManagerFactory != null;
    }
    
    @Override
    public AutorDao acessarMetodosDeManipulacaoDeAutor() {
        return manipuladorDeAutor;
    }

    @Override
    public EditoraDao acessarMetodosDeManipulacaoDeEditora() {
        return manipuladorDeEditora;
    }

    @Override
    public LivroDao acessarMetodosDeManipulacaoDeLivro() {
        return manipuladorDeLivro;
    }

    @Override
    public void fecharConexao() {
        entityManagerFactory.close();
    }
    
}
