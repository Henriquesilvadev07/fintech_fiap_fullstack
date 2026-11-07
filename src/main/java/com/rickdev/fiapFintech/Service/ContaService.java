package com.rickdev.fiapFintech.Service;

import com.rickdev.fiapFintech.Dto.ContaDto;
import com.rickdev.fiapFintech.Model.ContaModel;
import com.rickdev.fiapFintech.Repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<ContaModel> listarTodas() {
        return contaRepository.findAll();
    }

    public Optional<ContaModel> buscarPorId(Long id) {
        return contaRepository.findById(id);
    }

    public ContaModel salvar(ContaDto dto) {
        ContaModel conta = new ContaModel();
        conta.setAgencia(dto.agencia());
        conta.setNumeroConta(dto.numeroConta());
        conta.setSaldo(dto.saldo());
        return contaRepository.save(conta);
    }

    public Optional<ContaModel> atualizar(Long id, ContaDto dto) {
        return contaRepository.findById(id).map(conta -> {
            conta.setAgencia(dto.agencia());
            conta.setNumeroConta(dto.numeroConta());
            conta.setSaldo(dto.saldo());
            return contaRepository.save(conta);
        });
    }

    public boolean deletar(Long id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
