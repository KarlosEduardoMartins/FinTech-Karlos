package POO.TI22.FinTechKarlos.repository;

import POO.TI22.FinTechKarlos.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

    Optional<ContaCorrente> findByTitular_Cpf(String cpf);

}