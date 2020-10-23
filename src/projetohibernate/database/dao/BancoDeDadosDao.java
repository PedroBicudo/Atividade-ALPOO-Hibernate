/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.database.dao;

/**
 *
 * @author pedroh
 */
public interface BancoDeDadosDao {
    public AutorDao acessarMetodosDeManipulacaoDeAutor();
    public EditoraDao acessarMetodosDeManipulacaoDeEditora();
    public LivroDao acessarMetodosDeManipulacaoDeLivro();
    public void fecharConexao();
    
}
