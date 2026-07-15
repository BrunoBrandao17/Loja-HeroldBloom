package com.heroldbloom.heroldbloom_backend.controller;

import com.heroldbloom.heroldbloom_backend.dto.ProdutoRequestDTO;
import com.heroldbloom.heroldbloom_backend.dto.ProdutoResponseDTO;
import com.heroldbloom.heroldbloom_backend.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO deCadastro) {
        ProdutoResponseDTO novoProduto = produtoService.salvar(deCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO deAtualizacao) {
        ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, deAtualizacao);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna o status 204 No Content (sucesso sem corpo de resposta)
    }
}