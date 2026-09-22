package bc.com.HeroldBloom.spring_boot_essencials.dto;

import bc.com.HeroldBloom.spring_boot_essencials.model.ProdutoModel;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoResponseDTO {
    private Long id;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoProduto;
    private List<String> imagens;

    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Long id, String nomeProduto, String descricaoProduto, BigDecimal precoProduto, List<String> imagens) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.imagens = imagens;
    }

    public ProdutoResponseDTO(ProdutoModel produto) {
        this.id = produto.getId();
        this.nomeProduto = produto.getNomeProduto();
        this.descricaoProduto = produto.getDescricaoProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.imagens = produto.getImagens();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }
}
