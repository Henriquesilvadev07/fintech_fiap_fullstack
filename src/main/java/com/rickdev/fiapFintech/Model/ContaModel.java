package com.rickdev.fiapFintech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "T_FIN_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FIN_CONTA")
    @SequenceGenerator(name = "SQ_FIN_CONTA", sequenceName = "SQ_FIN_CONTA", allocationSize = 1)
    @Column(name = "id_conta")
    private Long id;

    @Column(name = "nr_agencia", nullable = false, length = 10)
    private String agencia;

    @Column(name = "nr_conta", nullable = false, length = 20)
    private String numeroConta;

    @Column(name = "vl_saldo", nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteModel cliente;
}