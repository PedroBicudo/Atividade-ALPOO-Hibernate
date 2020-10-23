/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedroh
 */
@Entity
@Table(name = "AUTOR")
public class Autor {

    @Id
    @Column(name = "ID_AUTOR")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IDFK_AUTOR")
    private List<Email> emails;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "autores")
    private List<Livro> livros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    public Autor() {
        setEmails(new ArrayList<>());
        setLivros(new ArrayList<>());
    }
}
