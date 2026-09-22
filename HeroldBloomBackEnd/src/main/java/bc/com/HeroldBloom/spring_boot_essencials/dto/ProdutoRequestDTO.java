package bc.com.HeroldBloom.spring_boot_essencials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoRequestDTO {
    @NotBlank(message = "O nome do produto é obrigatório")
    private  String nomeProduto;

    @NotBlank(message = "A descrição do produto é obrigatória")
    private String descricaoProduto;

    @NotNull(message = "É obrigatório colocar o preço do produto")
    @Positive(message = "O preço do produto deve ser maior que zero")
    private BigDecimal precoProduto;

    @NotEmpty(message = "O produto deve ter pelo menos uma imagem")
    private List<String> imagens;

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
