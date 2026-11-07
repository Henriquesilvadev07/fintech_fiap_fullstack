package com.rickdev.fiapFintech.Service;

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

    public ClienteModel salvar(ClienteModel usuario) {
        return clienteRepository.save(usuario);
    }

    public Optional<ClienteModel> atualizar(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deletar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
