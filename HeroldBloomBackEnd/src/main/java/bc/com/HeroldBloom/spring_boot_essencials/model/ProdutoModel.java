package bc.com.HeroldBloom.spring_boot_essencials.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tab_Produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private String descricaoProduto;

    @Column(nullable = false)
    private BigDecimal precoProduto;

    @ElementCollection
    @CollectionTable(
            name = "Tab_Produto_Imgs",
            joinColumns = @JoinColumn(name = "produto_id")
    )
    @Column(name = "Url_Img")
    private List<String> imagens = new ArrayList<>();

    public ProdutoModel() {
    }

    public ProdutoModel(Long id, String nomeProduto, String descricaoProduto, BigDecimal precoProduto, List<String> imagens) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.imagens = imagens;
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
