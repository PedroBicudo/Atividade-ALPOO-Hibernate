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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author pedroh
 */
@Entity
@Table(name = "LIVRO")
public class Livro {
    
    @Id
    @Column(name = "ISBN")
    private int id;
    
    @Column(name = "EDICAO", nullable = false)
    private int edicao;
    
    @Column(name = "CUSTO", nullable = false)
    private float custo;
    
    @Column(name = "TITULO", nullable = false, length = 100)
    private String titulo;
    
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "AUTORIA_LIVRO",
            joinColumns = {@JoinColumn(name = "IDFK_LIVRO")},
            inverseJoinColumns = {@JoinColumn(name = "IDFK_AUTOR")}
    )
    private List<Autor> autores;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Livro() {
        setAutores(new ArrayList<>());
    }
    
}
