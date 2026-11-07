package com.rickdev.fiapFintech.Service;

import com.rickdev.fiapFintech.Dto.ClienteDto;
import com.rickdev.fiapFintech.Model.ClienteModel;
import com.rickdev.fiapFintech.Repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {



    private final ClienteRepository clienteRepository;

    public List<ClienteModel> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteModel salvar(ClienteDto dto) {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setDataNascimento(dto.dataNascimento());
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> atualizar(Long id, ClienteDto dto) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(dto.nome());
            cliente.setEmail(dto.email());
            cliente.setCpf(dto.cpf());
            cliente.setDataNascimento(dto.dataNascimento());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
