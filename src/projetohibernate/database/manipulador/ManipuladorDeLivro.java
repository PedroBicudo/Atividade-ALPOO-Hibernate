/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.manipulador;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import projetohibernate.database.dao.LivroDao;
import projetohibernate.model.Livro;

/**
 *
 * @author pedroh
 */
public class ManipuladorDeLivro extends ManipuladorDeDadosDoBanco implements LivroDao {

    public ManipuladorDeLivro(EntityManagerFactory bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    public List<Livro> selecionarLivros() {
        EntityManager manager = null;
        List<Livro> livrosEncontrados = new ArrayList<>();
        
        try {
            manager = gerarEntityManager();
            Query query = manager.createQuery("FROM Livro L");
            livrosEncontrados = query.getResultList();
        
        } catch (Exception e) {
        
        } finally {
            if (manager != null) {
                manager.close();
            }            
        }
        
        return livrosEncontrados;
    }

    @Override
    public Livro selecionarLivroPorId(int id) {
        EntityManager manager = null;
        Livro livroEncontrado = null;
        
        try {
            manager = gerarEntityManager();
            livroEncontrado = manager.find(Livro.class, id);
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        
        } finally {
            if (manager != null) {
                manager.close();
            }            
        }
        
        return livroEncontrado;
    }

    @Override
    public boolean cadastrarLivro(Livro livro) {
        boolean isConcluido = false;
        EntityManager manager = null;

        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            livro = manager.merge(livro);
            manager.persist(livro);
            manager.getTransaction().commit();        
            isConcluido = true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        
        } finally {
            if (manager != null) {
                manager.close();
            }            
        }
        
        return isConcluido;
    }

    @Override
    public boolean removerLivro(Livro livro) {
        boolean isRemovido = false;
        EntityManager manager = null;
        
        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            livro = manager.merge(livro);
            manager.remove(livro);
            manager.getTransaction().commit();
            isRemovido = true;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        
        } finally {
            if (manager != null) {
                manager.close();
            }
            
        }
        
        return isRemovido;
    }
    
}
