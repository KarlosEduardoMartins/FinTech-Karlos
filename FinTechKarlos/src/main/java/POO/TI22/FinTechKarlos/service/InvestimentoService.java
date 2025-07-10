package POO.TI22.FinTechKarlos.service;

import POO.TI22.FinTechKarlos.factory.InvestimentoFactory;
import POO.TI22.FinTechKarlos.model.Investimento;
import POO.TI22.FinTechKarlos.model.InvestimentoAplicado;
import POO.TI22.FinTechKarlos.repository.InvestimentoAplicadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoAplicadoRepository investimentoAplicadoRepository;

    @Transactional
    public InvestimentoAplicado criarInvestimento(InvestimentoAplicado investimento) {
        return investimentoAplicadoRepository.save(investimento);
    }

    public List<InvestimentoAplicado> listarTodos() {
        return investimentoAplicadoRepository.findAll();
    }

    public InvestimentoAplicado buscarPorId(Long id) {
        return investimentoAplicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado para o ID: " + id));
    }

    @Transactional
    public InvestimentoAplicado atualizarInvestimento(Long id, InvestimentoAplicado dadosAtualizacao) {
        InvestimentoAplicado existente = buscarPorId(id);
        existente.setTipo(dadosAtualizacao.getTipo());
        existente.setValor(dadosAtualizacao.getValor());
        existente.setCliente(dadosAtualizacao.getCliente());
        return investimentoAplicadoRepository.save(existente);
    }

    @Transactional
    public void deletarInvestimento(Long id) {
        InvestimentoAplicado investimento = buscarPorId(id);
        investimentoAplicadoRepository.delete(investimento);
    }
    
    public String simularInvestimento(String tipo, double valor) {
        Investimento simulacao = InvestimentoFactory.createInvestimento(tipo, valor);
        
        String descricao = simulacao.getDescricao();
        String retorno = simulacao.calcularRetorno();

        return descricao + " " + retorno;
    }
}