package POO.TI22.FinTechKarlos.repository;

import POO.TI22.FinTechKarlos.model.InvestimentoAplicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoAplicadoRepository extends JpaRepository<InvestimentoAplicado, Long> {
}