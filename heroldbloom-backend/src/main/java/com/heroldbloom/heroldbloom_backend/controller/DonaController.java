package com.heroldbloom.heroldbloom_backend.controller;

import com.heroldbloom.heroldbloom_backend.dto.DonaRequestDTO;
import com.heroldbloom.heroldbloom_backend.dto.DonaResponseDTO;
import com.heroldbloom.heroldbloom_backend.dto.LoginRequestDTO;
import com.heroldbloom.heroldbloom_backend.service.DonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dona")
@CrossOrigin(origins = "*")
public class DonaController {

    @Autowired
    private DonaService donaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<DonaResponseDTO> cadastrar(@RequestBody @Valid DonaRequestDTO deCadastro) {
        DonaResponseDTO novaDona = donaService.cadastrar(deCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDona);
    }

    @PostMapping("/login")
    public ResponseEntity<DonaResponseDTO> login(@RequestBody @Valid LoginRequestDTO deLogin) {
        DonaResponseDTO donaLogada = donaService.login(deLogin);
        return ResponseEntity.ok(donaLogada);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<DonaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DonaRequestDTO deAtualizacao) {
        DonaResponseDTO donaAtualizada = donaService.atualizar(id, deAtualizacao);
        return ResponseEntity.ok(donaAtualizada);
    }
}