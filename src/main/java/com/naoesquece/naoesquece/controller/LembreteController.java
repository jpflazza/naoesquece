package com.naoesquece.naoesquece.controller;

import com.naoesquece.naoesquece.model.Prioridade;
import com.naoesquece.naoesquece.model.Status;
import com.naoesquece.naoesquece.service.LembreteService;
import com.naoesquece.naoesquece.dto.LembreteRequestDTO;
import com.naoesquece.naoesquece.dto.LembreteResponseDTO;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    // Cadastrar lembrete
    @PostMapping
    public ResponseEntity<LembreteResponseDTO> cadastrar(
            @Valid @RequestBody LembreteRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lembreteService.cadastrar(request));
    }

    // Listar todos ou filtrar por status e prioridade
    @GetMapping
    public ResponseEntity<List<LembreteResponseDTO>> listar(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Prioridade prioridade) {

        return ResponseEntity.ok(
                lembreteService.listarComFiltros(status, prioridade));
    }

    // Buscar lembrete por ID
    @GetMapping("/{id}")
    public ResponseEntity<LembreteResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(lembreteService.buscarPorId(id));
    }

    // Atualizar lembrete
    @PutMapping("/{id}")
    public ResponseEntity<LembreteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LembreteRequestDTO request) {

        return ResponseEntity.ok(
                lembreteService.atualizar(id, request));
    }

    // Concluir lembrete
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<LembreteResponseDTO> concluir(
            @PathVariable Long id) {

        return ResponseEntity.ok(lembreteService.concluir(id));
    }

    // Excluir lembrete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        lembreteService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // Buscar lembretes por período
    @GetMapping("/periodo")
    public ResponseEntity<List<LembreteResponseDTO>> buscarPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fim) {

        return ResponseEntity.ok(
                lembreteService.buscarPorPeriodo(inicio, fim));
    }

    // Listar lembretes atrasados
    @GetMapping("/atrasados")
    public ResponseEntity<List<LembreteResponseDTO>> listarAtrasados() {

        return ResponseEntity.ok(lembreteService.listarAtrasados());
    }
}