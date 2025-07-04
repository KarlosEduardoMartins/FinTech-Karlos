package POO.TI22.FinTechKarlos.repository;

import POO.TI22.FinTechKarlos.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
}