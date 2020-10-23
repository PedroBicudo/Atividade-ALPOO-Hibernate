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
import projetohibernate.database.dao.AutorDao;
import projetohibernate.model.Autor;
import projetohibernate.model.Livro;

/**
 *
 * @author pedroh
 */
public class ManipuladorDeAutor extends ManipuladorDeDadosDoBanco implements AutorDao {

    public ManipuladorDeAutor(EntityManagerFactory bancoDeDados) {
        super(bancoDeDados);
    }
    
    @Override
    public List<Autor> selecionarAutores() {
        EntityManager manager = null;
        List<Autor> autoresEncontrados = new ArrayList<>();
        
        try  {
            manager = gerarEntityManager();
            Query query = manager.createQuery("FROM Autor A");
            autoresEncontrados.addAll((List<Autor>) query.getResultList());
        
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return autoresEncontrados;
    }

    @Override
    public List<Autor> selecionarNomeECodigoDosAutores() {
        EntityManager manager = null;
        List<Autor> autoresEncontrados = new ArrayList<>();
        
        try  {
            manager = gerarEntityManager();
            Query query = manager.createQuery("SELECT A.id, A.nome FROM Autor A");
            List<Object[]> colunasSelecionadas = query.getResultList();
            autoresEncontrados = vincularColunasNomeEIdComOsAutores(colunasSelecionadas);
        
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return autoresEncontrados;
    }
    
    private List<Autor> vincularColunasNomeEIdComOsAutores(List<Object[]> listaDeColunasSelecionadas) {
        List<Autor> autores = new ArrayList<>();
        
        for (Object[] colunasAutor: listaDeColunasSelecionadas) {
            Autor autor = new Autor();
            autor.setId((int) colunasAutor[0]);
            autor.setNome((String) colunasAutor[1]);
            
            autores.add(autor);
        }
        
        return autores;
    }
    
    @Override
    public Autor selecionarAutorPorId(int id) {
        EntityManager manager = null;
        Autor autorEncontrado = null;
        
        try {
            manager = gerarEntityManager();
            Query query = manager.createQuery("FROM Autor A WHERE A.id="+id);
            autorEncontrado = (Autor) query.getSingleResult();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        
        return autorEncontrado;
    }

    @Override
    public boolean cadastrarAutor(Autor autor) {
        boolean isConcluido = false;
        EntityManager manager = null;
        
        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            autor = manager.merge(autor);
            manager.persist(autor);
            
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
    public boolean removerAutor(Autor autor) {
        boolean isRemovido = false;
        EntityManager manager = null;
        
        try {
            manager = gerarEntityManager();
            manager.getTransaction().begin();
            removerLivrosDeUnicoAutorAssociadosAoIdDoAutor(manager, autor.getId());
            autor = manager.merge(autor);
            manager.remove(autor);
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
    
    private void removerLivrosDeUnicoAutorAssociadosAoIdDoAutor(EntityManager manager, int idAutor) {
        List<Integer> idLivrosAssociadosSomenteAoAutor = obterIdDeLivrosAssociadosSomenteAoAutor(manager, idAutor);
        for (int idLivro: idLivrosAssociadosSomenteAoAutor) {
            Livro livro = manager.find(Livro.class, idLivro);
            manager.remove(livro);
        }
    }
    
    private List<Integer> obterIdDeLivrosAssociadosSomenteAoAutor(EntityManager manager, int idAutor) {
        Query idsAssociadosSomenteAoAutor = manager.createNativeQuery("SELECT IDFK_LIVRO FROM AUTORIA_LIVRO WHERE IDFK_LIVRO IN (SELECT AL.IDFK_LIVRO FROM AUTORIA_LIVRO AL GROUP BY AL.IDFK_LIVRO HAVING COUNT(AL.IDFK_LIVRO) = 1) AND IDFK_AUTOR = ?;");
        idsAssociadosSomenteAoAutor.setParameter(1, idAutor);
        return idsAssociadosSomenteAoAutor.getResultList();
    }
}

