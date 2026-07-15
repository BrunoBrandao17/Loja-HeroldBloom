package com.heroldbloom.heroldbloom_backend.dto;

import com.heroldbloom.heroldbloom_backend.model.ProdutoModel;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String urlImagem;
    private List<String> imagens;

    public ProdutoResponseDTO(ProdutoModel produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.urlImagem = produto.getUrlImagem();
        this.imagens = produto.getImagens().stream()
                .map(img -> img.getUrlImagem())
                .collect(Collectors.toList());
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Double getPreco() { return preco; }
    public String getUrlImagem() { return urlImagem; }
    public List<String> getImagens() { return imagens; }
}