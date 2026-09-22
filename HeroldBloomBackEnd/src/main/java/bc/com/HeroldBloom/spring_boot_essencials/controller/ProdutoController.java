package bc.com.HeroldBloom.spring_boot_essencials.controller;

import bc.com.HeroldBloom.spring_boot_essencials.dto.ProdutoRequestDTO;
import bc.com.HeroldBloom.spring_boot_essencials.dto.ProdutoResponseDTO;
import bc.com.HeroldBloom.spring_boot_essencials.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO novoProduto = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{nomeProduto}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorNome(@PathVariable String nomeProduto) {
        ProdutoResponseDTO produto = produtoService.buscarPorNome(nomeProduto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{nomeProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable String nomeProduto,
            @RequestBody @Valid ProdutoRequestDTO dto) {

        ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(nomeProduto, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{nomeProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable String nomeProduto) {
        produtoService.deletar(nomeProduto);
        return ResponseEntity.noContent().build();
    }
}