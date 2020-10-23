/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.dao;

import projetohibernate.model.Autor;
import java.util.List;

/**
 *
 * @author pedroh
 */
public interface AutorDao {
    List<Autor> selecionarAutores();
    List<Autor> selecionarNomeECodigoDosAutores();
    Autor selecionarAutorPorId(int id);
    boolean cadastrarAutor(Autor autor);
    boolean removerAutor(Autor autor);
}
