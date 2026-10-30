package com.naoesquece.naoesquece.model;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lembrete")
public class Lembrete {
    @Enumerated(EnumType.STRING)
    @Id
    private Long id;
    private String titulo;
    private String descricao;
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    private Prioridade prioridade;
    private Status status = Status.PENDENTE;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}