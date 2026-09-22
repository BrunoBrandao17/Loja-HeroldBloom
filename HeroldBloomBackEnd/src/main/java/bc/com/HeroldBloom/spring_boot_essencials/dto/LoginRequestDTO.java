package bc.com.HeroldBloom.spring_boot_essencials.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Usuário obrigatório") String login,
        @NotBlank(message = "Senha obrigatória") String senha
) {}
