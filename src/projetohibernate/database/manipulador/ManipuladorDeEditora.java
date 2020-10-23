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
import projetohibernate.database.dao.EditoraDao;
import projetohibernate.model.Editora;

/**
 *
 * @author pedroh
 */
public class ManipuladorDeEditora extends ManipuladorDeDadosDoBanco implements EditoraDao {

    public ManipuladorDeEditora(EntityManagerFactory bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    public List<Editora> selecionarEditoras() {
        EntityManager manager = null;
        List<Editora> editorasEncontradas = new ArrayList<>();
        
        try {
            manager = gerarEntityManager();
            Query query = manager.createQuery("FROM Editora E");
            editorasEncontradas = query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return editorasEncontradas;
    }

    @Override
    public Editora selecionarEditoraPorId(int id) {
        EntityManager manager = null;
        Editora editoraEncontrada = null;
        
        try {
            manager = gerarEntityManager();
            editoraEncontrada = manager.find(Editora.class, id);
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return editoraEncontrada;
    }

    @Override
    public boolean cadastrarEditora(Editora editora) {
        boolean isConcluido = false;
        EntityManager manager = null;
        
        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            editora = manager.merge(editora);
            manager.persist(editora);
            manager.getTransaction().commit();
            isConcluido = true;

        } catch (Exception e) {
            manager.getTransaction().rollback();
        
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return isConcluido;
    }

    @Override
    public boolean removerEditora(Editora editora) {
        boolean isRemovido = false;
        EntityManager manager = null;
        
        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            editora = manager.merge(editora);
            manager.remove(editora);
            manager.getTransaction().commit();
            isRemovido = true;
        
        } catch (Exception e) {
            manager.getTransaction().rollback();
        
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return isRemovido;
    }
    
    
}
