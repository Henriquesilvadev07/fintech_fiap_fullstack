package com.rickdev.fiapFintech.Controller;

import com.rickdev.fiapFintech.Dto.ContaDto;
import com.rickdev.fiapFintech.Model.ContaModel;
import com.rickdev.fiapFintech.Service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;


    @GetMapping
    public ResponseEntity<List<ContaModel>> listarTodas() {
        return ResponseEntity.ok(contaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaModel> buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContaModel> criar(@RequestBody ContaDto dto) {
        ContaModel conta = contaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaModel> atualizar(@PathVariable Long id, @RequestBody ContaDto dto) {
        return contaService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (contaService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
