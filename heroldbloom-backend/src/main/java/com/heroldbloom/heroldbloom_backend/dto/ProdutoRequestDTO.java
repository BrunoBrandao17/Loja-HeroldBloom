package com.heroldbloom.heroldbloom_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição do produto é obrigatória.")
    private String description;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    @NotBlank(message = "A imagem principal é obrigatória.")
    private String urlImagem;

    // Lista de strings (URLs) enviadas pelo front para o carrossel de fotos
    private List<String> imagensUrl;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(String nome, String description, Double preco, String urlImagem, List<String> imagensUrl) {
        this.nome = nome;
        this.description = description;
        this.preco = preco;
        this.urlImagem = urlImagem;
        this.imagensUrl = imagensUrl;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }

    public List<String> getImagensUrl() { return imagensUrl; }
    public void setImagensUrl(List<String> imagensUrl) { this.imagensUrl = imagensUrl; }
}