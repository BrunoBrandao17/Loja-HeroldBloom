package bc.com.HeroldBloom.spring_boot_essencials.service;

import bc.com.HeroldBloom.spring_boot_essencials.dto.LoginRequestDTO;
import bc.com.HeroldBloom.spring_boot_essencials.model.UsuarioModel;
import bc.com.HeroldBloom.spring_boot_essencials.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String cadastrarDona(LoginRequestDTO dto){
        if (usuarioRepository.count()>0){
            throw new RuntimeException("O cadastro de admin ja foi realizado. em caso de perca da conta falar com o Dev");
        }

        UsuarioModel dona = new UsuarioModel();
        dona.setLogin(dto.login());
        dona.setSenha(passwordEncoder.encode(dto.senha()));

        usuarioRepository.save(dona);
        return "Perfil de admin cadastrada com sucesso";
    }

    public String autenticar(LoginRequestDTO dto){
        UsuarioModel usuario = usuarioRepository.findByLogin(dto.login())
                .orElseThrow(()-> new RuntimeException("Usuário ou senha inválido"));
        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())){
            throw new RuntimeException("Usuário ou senha inválido");
        }

        return "Login realizado com sucesso";
    }
}
