/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.dao;

import java.util.List;
import projetohibernate.model.Livro;

/**
 *
 * @author pedroh
 */
public interface LivroDao {
    List<Livro> selecionarLivros();
    Livro selecionarLivroPorId(int id);
    boolean cadastrarLivro(Livro livro);
    boolean removerLivro(Livro livro);    
}
