package com.heroldbloom.heroldbloom_backend.service;

import com.heroldbloom.heroldbloom_backend.dto.ProdutoRequestDTO;
import com.heroldbloom.heroldbloom_backend.dto.ProdutoResponseDTO;
import com.heroldbloom.heroldbloom_backend.model.ProdutoImagemModel;
import com.heroldbloom.heroldbloom_backend.model.ProdutoModel;
import com.heroldbloom.heroldbloom_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescription());
        produto.setPreco(request.getPreco());
        produto.setUrlImagem(request.getUrlImagem());

        if (request.getImagensUrl() != null && !request.getImagensUrl().isEmpty()) {
            for (String url : request.getImagensUrl()) {
                ProdutoImagemModel imgModel = new ProdutoImagemModel();
                imgModel.setUrlImagem(url);
                imgModel.setProduto(produto);

                produto.getImagens().add(imgModel);
            }
        }

            ProdutoModel produtoSalvo = produtoRepository.save(produto);

            return new ProdutoResponseDTO(produtoSalvo);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO request) {
        ProdutoModel produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produtoExistente.setNome(request.getNome());
        produtoExistente.setDescricao(request.getDescription());
        produtoExistente.setPreco(request.getPreco());
        produtoExistente.setUrlImagem(request.getUrlImagem());

        produtoExistente.getImagens().clear();
        if (request.getImagensUrl() != null && !request.getImagensUrl().isEmpty()) {
            for (String url : request.getImagensUrl()) {
                ProdutoImagemModel imgModel = new ProdutoImagemModel();
                imgModel.setUrlImagem(url);
                imgModel.setProduto(produtoExistente);
                produtoExistente.getImagens().add(imgModel);
            }
        }

        ProdutoModel produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        // Verifica se o produto existe antes de tentar deletar
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}