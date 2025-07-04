package POO.TI22.FinTechKarlos.repository;

import POO.TI22.FinTechKarlos.model.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Long> {

    Optional<ContaPoupanca> findByTitular_Cpf(String cpf);
}