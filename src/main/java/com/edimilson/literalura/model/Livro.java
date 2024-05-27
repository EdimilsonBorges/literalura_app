package com.edimilson.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String titulo;
    private Integer numeroDownloads;
    @Column(length = 5)
    private String idioma;
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idioma().getFirst();
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    public Livro(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.setLivro(this));
        this.autores = autores;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        String nomeAutor = !getAutores().isEmpty() ? getAutores().getFirst().getNome() : "Autor desconhecido";
        return String.format("""
                --------------------------------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de Downloads: %s
                --------------------------------
                """, getTitulo(), nomeAutor, getIdioma(), getNumeroDownloads());
    }
}