package bc.com.HeroldBloom.spring_boot_essencials.controller;

import bc.com.HeroldBloom.spring_boot_essencials.dto.LoginRequestDTO;
import bc.com.HeroldBloom.spring_boot_essencials.dto.TokenResponseDTO;
import bc.com.HeroldBloom.spring_boot_essencials.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarDona(@Valid @RequestBody LoginRequestDTO dto){
        String mensagem = authService.cadastrarDona(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PostMapping("/Login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto){
        String token = authService.autenticar(dto);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
