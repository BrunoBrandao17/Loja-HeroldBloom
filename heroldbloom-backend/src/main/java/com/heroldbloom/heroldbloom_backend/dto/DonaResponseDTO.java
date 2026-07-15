package com.heroldbloom.heroldbloom_backend.dto;

import com.heroldbloom.heroldbloom_backend.model.DonaModel;

public class DonaResponseDTO {

    private Long id;
    private String nome;
    private String email;

    public DonaResponseDTO(DonaModel dona) {
        this.id = dona.getId();
        this.nome = dona.getNome();
        this.email = dona.getEmail();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}