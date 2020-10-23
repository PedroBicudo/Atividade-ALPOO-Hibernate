/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.manipulador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pedroh
 */
public abstract class ManipuladorDeDadosDoBanco {
    
    private EntityManagerFactory bancoDeDados;

    public ManipuladorDeDadosDoBanco(EntityManagerFactory bancoDeDados) {
        setBancoDeDados(bancoDeDados);
    }

    private EntityManagerFactory getBancoDeDados() {
        return bancoDeDados;
    }

    private void setBancoDeDados(EntityManagerFactory bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }
    
    protected EntityManager gerarEntityManager() {
        return getBancoDeDados().createEntityManager();
    }
    
}
