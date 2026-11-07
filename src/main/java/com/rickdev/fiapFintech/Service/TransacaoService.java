package com.rickdev.fiapFintech.Service;

import com.rickdev.fiapFintech.Model.ContaModel;
import com.rickdev.fiapFintech.Model.TransacaoModel;
import com.rickdev.fiapFintech.Repository.ContaRepository;
import com.rickdev.fiapFintech.Repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;


    public List<TransacaoModel> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Optional<TransacaoModel> buscarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    @Transactional
    public TransacaoModel realizarTransacao(TransacaoModel transacao) {
        ContaModel conta = contaRepository.findById(transacao.getConta().getId())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        BigDecimal novoSaldo = conta.getSaldo();
        if ("SAQUE".equalsIgnoreCase(transacao.getTipo()) || "PIX".equalsIgnoreCase(transacao.getTipo())) {
            novoSaldo = novoSaldo.subtract(transacao.getValor());
        } else if ("DEPOSITO".equalsIgnoreCase(transacao.getTipo())) {
            novoSaldo = novoSaldo.add(transacao.getValor());
        }

        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);

        transacao.setConta(conta);
        return transacaoRepository.save(transacao);
    }

    public boolean deletar(Long id) {
        if (transacaoRepository.existsById(id)) {
            transacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
