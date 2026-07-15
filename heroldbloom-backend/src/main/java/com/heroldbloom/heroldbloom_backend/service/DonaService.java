package com.heroldbloom.heroldbloom_backend.service;

import com.heroldbloom.heroldbloom_backend.dto.DonaRequestDTO;
import com.heroldbloom.heroldbloom_backend.dto.DonaResponseDTO;
import com.heroldbloom.heroldbloom_backend.dto.LoginRequestDTO;
import com.heroldbloom.heroldbloom_backend.model.DonaModel;
import com.heroldbloom.heroldbloom_backend.repository.DonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonaService {

    @Autowired
    private DonaRepository donaRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public DonaResponseDTO cadastrar(DonaRequestDTO request) {
        if (donaRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema!");
        }

        DonaModel dona = new DonaModel();
        dona.setNome(request.getNome());
        dona.setEmail(request.getEmail());

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());
        dona.setSenha(senhaCriptografada);

        DonaModel donaSalva = donaRepository.save(dona);
        return new DonaResponseDTO(donaSalva);
    }

    @Transactional(readOnly = true)
    public DonaResponseDTO login(LoginRequestDTO request) {
        DonaModel dona = donaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos."));

        if (!passwordEncoder.matches(request.getSenha(), dona.getSenha())) {
            throw new RuntimeException("E-mail ou senha incorretos.");
        }

        return new DonaResponseDTO(dona);
    }

    @Transactional
    public DonaResponseDTO atualizar(Long id, DonaRequestDTO request) {
        DonaModel dona = donaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dona não encontrada com o ID: " + id));

        donaRepository.findByEmail(request.getEmail())
                .ifPresent(existente -> {
                    if (!existente.getId().equals(id)) {
                        throw new RuntimeException("Este e-mail já está sendo usado por outro usuário!");
                    }
                });

        dona.setNome(request.getNome());
        dona.setEmail(request.getEmail());

        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            dona.setSenha(passwordEncoder.encode(request.getSenha()));
        }

        DonaModel donaAtualizada = donaRepository.save(dona);
        return new DonaResponseDTO(donaAtualizada);
    }

    @Transactional
    public void deletar(Long id) {
        DonaModel dona = donaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dona não encontrada com o ID: " + id));
        donaRepository.delete(dona);
    }
}