package bc.com.HeroldBloom.spring_boot_essencials.service;

import bc.com.HeroldBloom.spring_boot_essencials.dto.ProdutoRequestDTO;
import bc.com.HeroldBloom.spring_boot_essencials.dto.ProdutoResponseDTO;
import bc.com.HeroldBloom.spring_boot_essencials.model.ProdutoModel;
import bc.com.HeroldBloom.spring_boot_essencials.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto){
        ProdutoModel produto = new ProdutoModel();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setDescricaoProduto(dto.getDescricaoProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setImagens(dto.getImagens());

        ProdutoModel produtoSalvo = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produtoSalvo);
    }

    public ProdutoResponseDTO atualizarProduto(String nomeProduto, ProdutoRequestDTO dto){
        ProdutoModel produto = produtoRepository.findByNomeProduto(nomeProduto)
                .orElseThrow(()-> new RuntimeException("Produto com o nome: " + nomeProduto + " não encontrado"));

        if (dto.getNomeProduto() != null && !dto.getNomeProduto().isBlank()){
            produto.setNomeProduto(dto.getNomeProduto());
        }
        if (dto.getDescricaoProduto() != null && !dto.getDescricaoProduto(). isBlank()){
            produto.setDescricaoProduto((dto.getDescricaoProduto()));
        }
        if (dto.getPrecoProduto() != null && dto.getPrecoProduto().compareTo(BigDecimal.ZERO)> 0){
            produto.setPrecoProduto(dto.getPrecoProduto());
        }
        if (dto.getImagens() != null && !dto.getImagens().isEmpty()){
            produto.getImagens().clear(); //Limpa as imagens antigas para não duplicar
            produto.getImagens().addAll(dto.getImagens());
        }

        ProdutoModel produtoAtualizado = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produtoAtualizado);
    }

    public List<ProdutoResponseDTO> listarTodos(){
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO buscarPorNome(String nomeProduto){
        ProdutoModel produto = produtoRepository.findByNomeProduto(nomeProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto);
    }

    public void deletar(String nomeProduto){
        ProdutoModel produto = produtoRepository.findByNomeProduto(nomeProduto)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }
}
