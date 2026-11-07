package com.rickdev.fiapFintech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_FIN_TRANSACAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FIN_TRANSACAO")
    @SequenceGenerator(name = "SQ_FIN_TRANSACAO", sequenceName = "SQ_FIN_TRANSACAO", allocationSize = 1)
    @Column(name = "id_transacao")
    private Long id;

    @Column(name = "tp_transacao", nullable = false, length = 20)
    private String tipo; // DEPOSITO, SAQUE, PIX, TRANSFERENCIA

    @Column(name = "vl_transacao", nullable = false)
    private BigDecimal valor;

    @Column(name = "dt_transacao", nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private ContaModel conta;
}
