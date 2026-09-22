package bc.com.HeroldBloom.spring_boot_essencials.repository;

import bc.com.HeroldBloom.spring_boot_essencials.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel>findByLogin(String login);
}
