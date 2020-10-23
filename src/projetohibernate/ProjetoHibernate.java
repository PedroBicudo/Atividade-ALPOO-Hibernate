/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.eclipse.persistence.jpa.PersistenceProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import projetohibernate.database.BancoDeDados;
import projetohibernate.database.dao.BancoDeDadosDao;
import projetohibernate.model.Autor;
import projetohibernate.model.Editora;
import projetohibernate.model.Email;
import projetohibernate.model.Livro;

/**
 *
 * @author pedroh
 */
public class ProjetoHibernate {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BancoDeDadosDao bancoDeDadoSQLServer = new BancoDeDados();

        for (Autor autor: gerarAutores()) {
            bancoDeDadoSQLServer.acessarMetodosDeManipulacaoDeAutor().cadastrarAutor(autor);
        }

        List<Autor> autores = bancoDeDadoSQLServer.acessarMetodosDeManipulacaoDeAutor().selecionarAutores();
        List<List<Autor>> a = gerarListaDeAutoresParaCadaLivro(autores);        

        for (Editora ed: gerarEditoras(a)) {
            bancoDeDadoSQLServer.acessarMetodosDeManipulacaoDeEditora().cadastrarEditora(ed);
        }
        
        operacoesComAutor(bancoDeDadoSQLServer);
        operacoesComLivro(bancoDeDadoSQLServer, autores);
        operacoesComEditora(bancoDeDadoSQLServer);
        
        bancoDeDadoSQLServer.fecharConexao();

    }
    
    public static void operacoesComAutor(BancoDeDadosDao bancoDeDados) {
        Autor autor = bancoDeDados.acessarMetodosDeManipulacaoDeAutor()
                .selecionarAutorPorId(1);

        List<Autor> autores = bancoDeDados.acessarMetodosDeManipulacaoDeAutor()
                .selecionarAutores();
        
        List<Autor> autoresComCodigoENome = bancoDeDados.acessarMetodosDeManipulacaoDeAutor()
                .selecionarNomeECodigoDosAutores();
        
        bancoDeDados.acessarMetodosDeManipulacaoDeAutor()
                .removerAutor(autor);
    }
    
    public static void operacoesComLivro(BancoDeDadosDao bancoDeDados, List<Autor> autores) {
        Livro livroNovo = new Livro();
        livroNovo.setAutores(gerarListaDeAutoresParaCadaLivro(autores).get(3));
        livroNovo.setCusto(155F);
        livroNovo.setEdicao(10);
        livroNovo.setTitulo("Uma história como qualquer outra");
        livroNovo.setId(12121212);

        bancoDeDados.acessarMetodosDeManipulacaoDeLivro().cadastrarLivro(livroNovo);
        
        Livro livro = bancoDeDados.acessarMetodosDeManipulacaoDeLivro()
                .selecionarLivroPorId(22222222);
        
        List<Livro> livros = bancoDeDados.acessarMetodosDeManipulacaoDeLivro()
                .selecionarLivros();
        
        bancoDeDados.acessarMetodosDeManipulacaoDeLivro()
                .removerLivro(livro);
    }    

    public static void operacoesComEditora(BancoDeDadosDao bancoDeDados) {
        Editora editora = bancoDeDados.acessarMetodosDeManipulacaoDeEditora()
                .selecionarEditoraPorId(25);
        
        List<Editora> editoras = bancoDeDados.acessarMetodosDeManipulacaoDeEditora()
                .selecionarEditoras();
        
        bancoDeDados.acessarMetodosDeManipulacaoDeEditora()
                .removerEditora(editora);
    }
    
    public static List<Autor> gerarAutores() {
        List<Autor> autores = new ArrayList<>();
        List<List<Email>> emails = gerarEmailsParaCadaAutor();
        
        Autor autorPrimeiro = new Autor();
        autorPrimeiro.setEmails(emails.get(0));
        autorPrimeiro.setNome("Autor A");
        
        Autor autorSegundo = new Autor();
        autorSegundo.setEmails(emails.get(1));
        autorSegundo.setNome("Autor B");
        
        Autor autorTerceiro = new Autor();
        autorTerceiro.setEmails(emails.get(2));
        autorTerceiro.setNome("Autor C");
        
        Autor autorQuarto = new Autor();
        autorQuarto.setEmails(emails.get(3));
        autorQuarto.setNome("Autor D");        

        Autor autorQuinto = new Autor();
        autorQuinto.setEmails(emails.get(4));
        autorQuinto.setNome("Autor E");        
        
        autores.add(autorPrimeiro);
        autores.add(autorSegundo);
        autores.add(autorTerceiro);
        autores.add(autorQuarto);
        autores.add(autorQuinto);
        
        return autores;
    }
    
    public static List<List<Email>> gerarEmailsParaCadaAutor() {
        List<List<Email>> emailsDeCadaAutor = new ArrayList<>();
        
        Email emailPrimeiro = new Email();
        emailPrimeiro.setEmail("a@gmail.com");
        
        Email emailSegundo = new Email();
        emailSegundo.setEmail("b@gmail.com");
        
        Email emailTerceiro = new Email();
        emailTerceiro.setEmail("c@gmail.com");
        
        Email emailQuarto = new Email();
        emailQuarto.setEmail("d@gmail.com");
        
        Email emailQuinto = new Email();
        emailQuinto.setEmail("e@gmail.com");
        
        ArrayList<Email> emailsPrimeiroAutor = new ArrayList<>();
        emailsPrimeiroAutor.add(emailPrimeiro);
        
        ArrayList<Email> emailsSegundoAutor = new ArrayList<>();
        emailsSegundoAutor.add(emailSegundo);
        
        ArrayList<Email> emailsTerceiroAutor = new ArrayList<>();
        emailsTerceiroAutor.add(emailTerceiro);

        ArrayList<Email> emailsQuartoAutor = new ArrayList<>();
        emailsQuartoAutor.add(emailQuarto);

        ArrayList<Email> emailsQuintoAutor = new ArrayList<>();
        emailsQuintoAutor.add(emailQuinto);   
        
        emailsDeCadaAutor.add(emailsPrimeiroAutor);
        emailsDeCadaAutor.add(emailsSegundoAutor);
        emailsDeCadaAutor.add(emailsTerceiroAutor);
        emailsDeCadaAutor.add(emailsQuartoAutor);
        emailsDeCadaAutor.add(emailsQuintoAutor);

        return emailsDeCadaAutor;
    }
    
    public static List<Editora> gerarEditoras(List<List<Autor>> autores) {
        List<Editora> editoras = new ArrayList<>();
        List<List<Livro>> livrosDeCadaEditora = gerarLivrosDeCadaEditora(autores);
        
        Editora editoraPrimeiro = new Editora();
        editoraPrimeiro.setNome("Editora V");
        editoraPrimeiro.setLivros(livrosDeCadaEditora.get(0));

        Editora editoraSegundo = new Editora();
        editoraSegundo.setNome("Editora W");
        editoraSegundo.setLivros(livrosDeCadaEditora.get(1));
        
        Editora editoraTerceiro = new Editora();
        editoraTerceiro.setNome("Editora X");
        editoraTerceiro.setLivros(livrosDeCadaEditora.get(2));

        Editora editoraQuarto = new Editora();
        editoraQuarto.setNome("Editora Y");
        editoraQuarto.setLivros(livrosDeCadaEditora.get(3));        

        Editora editoraQuinto = new Editora();
        editoraQuinto.setId(25);
        editoraQuinto.setNome("Editora Z");
        editoraQuinto.setLivros(livrosDeCadaEditora.get(4));        
        
        editoras.add(editoraPrimeiro);
        editoras.add(editoraSegundo);
        editoras.add(editoraTerceiro);
        editoras.add(editoraQuarto);
        editoras.add(editoraQuinto);
        
        return editoras;
    }

    public static List<List<Livro>> gerarLivrosDeCadaEditora(List<List<Autor>> autores) {
        List<List<Livro>> livrosDeCadaEditora = new ArrayList<>();
        
        Livro livroPrimeiro = new Livro();
        livroPrimeiro.setId(11111111);
        livroPrimeiro.setCusto(12F);
        livroPrimeiro.setEdicao(1);
        livroPrimeiro.setTitulo("Livro A");
        livroPrimeiro.setAutores(autores.get(0));
        
        Livro livroSegundo = new Livro();
        livroSegundo.setId(22222222);
        livroSegundo.setCusto(100F);
        livroSegundo.setEdicao(2);
        livroSegundo.setTitulo("Livro B");
        livroSegundo.setAutores(autores.get(1));
        
        Livro livroTerceiro = new Livro();
        livroTerceiro.setId(33333333);
        livroTerceiro.setCusto(1F);
        livroTerceiro.setEdicao(3);
        livroTerceiro.setTitulo("Livro C");
        livroTerceiro.setAutores(autores.get(2));        
        
        Livro livroQuarto = new Livro();
        livroQuarto.setId(44444444);
        livroQuarto.setCusto(50F);
        livroQuarto.setEdicao(1);
        livroQuarto.setTitulo("Livro D");
        livroQuarto.setAutores(autores.get(3));        

        Livro livroQuinto = new Livro();
        livroQuinto.setId(55555555);
        livroQuinto.setCusto(18F);
        livroQuinto.setEdicao(1);
        livroQuinto.setTitulo("Livro E");
        livroQuinto.setAutores(autores.get(4));
        
        List<Livro> livrosPrimeiraEditora = new ArrayList<>();
        livrosPrimeiraEditora.add(livroPrimeiro);
        
        List<Livro> livrosSegundaEditora = new ArrayList<>();
        livrosSegundaEditora.add(livroSegundo);
        
        List<Livro> livrosTerceiraEditora = new ArrayList<>();
        livrosTerceiraEditora.add(livroTerceiro);
        
        List<Livro> livrosQuartaEditora = new ArrayList<>();
        livrosQuartaEditora.add(livroQuarto);
        
        List<Livro> livrosQuintaEditora = new ArrayList<>();
        livrosQuintaEditora.add(livroQuinto);        
        
        livrosDeCadaEditora.add(livrosPrimeiraEditora);
        livrosDeCadaEditora.add(livrosSegundaEditora);
        livrosDeCadaEditora.add(livrosTerceiraEditora);
        livrosDeCadaEditora.add(livrosQuartaEditora);
        livrosDeCadaEditora.add(livrosQuintaEditora);
        
        return livrosDeCadaEditora;
    }
    
    public static List<List<Autor>> gerarListaDeAutoresParaCadaLivro(List<Autor> autores) {
        List<List<Autor>> listaDeAutoresParaCadaLivro = new ArrayList<List<Autor>>();
        
        ArrayList<Autor> primeiroLivro = new ArrayList<>();
        primeiroLivro.add(autores.get(0));
        primeiroLivro.add(autores.get(1));

        ArrayList<Autor> segundoLivro = new ArrayList<>();
        segundoLivro.add(autores.get(0));
        segundoLivro.add(autores.get(2));
        
        ArrayList<Autor> terceiroLivro = new ArrayList<>();
        terceiroLivro.add(autores.get(1));
        terceiroLivro.add(autores.get(3));
        
        ArrayList<Autor> quartoLivro = new ArrayList<>();
        quartoLivro.add(autores.get(4));        
        
        ArrayList<Autor> quintoLivro = new ArrayList<>();
        quintoLivro.add(autores.get(3));
        
        listaDeAutoresParaCadaLivro.add(primeiroLivro);
        listaDeAutoresParaCadaLivro.add(segundoLivro);
        listaDeAutoresParaCadaLivro.add(terceiroLivro);
        listaDeAutoresParaCadaLivro.add(quartoLivro);
        listaDeAutoresParaCadaLivro.add(quintoLivro);
        
        return listaDeAutoresParaCadaLivro;
    }    
    
}
