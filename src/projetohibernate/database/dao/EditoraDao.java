/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.dao;

import java.util.List;
import projetohibernate.model.Editora;

/**
 *
 * @author pedroh
 */
public interface EditoraDao {
    List<Editora> selecionarEditoras();
    Editora selecionarEditoraPorId(int id);
    boolean cadastrarEditora(Editora editora);
    boolean removerEditora(Editora editora);    
}
