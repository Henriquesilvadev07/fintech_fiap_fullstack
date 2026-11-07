package com.rickdev.fiapFintech.Controller;

import com.rickdev.fiapFintech.Model.TransacaoModel;
import com.rickdev.fiapFintech.Service.TransacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;


    @GetMapping
    public ResponseEntity<List<TransacaoModel>> listarTodas() {
        return ResponseEntity.ok(transacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoModel> buscarPorId(@PathVariable Long id) {
        return transacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransacaoModel> criar(@RequestBody TransacaoModel transacao) {
        TransacaoModel novaTransacao = transacaoService.realizarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (transacaoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}