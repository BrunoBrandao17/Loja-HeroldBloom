package com.heroldbloom.heroldbloom_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "table_produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private String urlImagem;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<ProdutoImagemModel> imagens = new java.util.ArrayList<>();

    public java.util.List<ProdutoImagemModel> getImagens() { return imagens; }
    public void setImagens(java.util.List<ProdutoImagemModel> imagens) { this.imagens = imagens; }

    public ProdutoModel() {
    }

    public ProdutoModel(Long id, String nome, String descricao, Double preco, String urlImagem, List<ProdutoImagemModel> imagens) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.urlImagem = urlImagem;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
