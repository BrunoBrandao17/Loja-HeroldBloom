package bc.com.HeroldBloom.spring_boot_essencials.repository;

import bc.com.HeroldBloom.spring_boot_essencials.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    Optional<ProdutoModel> findByNomeProduto (String nomeProduto);
}
