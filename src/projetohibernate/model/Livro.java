/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetohibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDFK_EDITORA", referencedColumnName = "ID_EDITORA")
    private Editora editora;

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

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Livro() {
        setEditora(new Editora());
    }
    
    
}
