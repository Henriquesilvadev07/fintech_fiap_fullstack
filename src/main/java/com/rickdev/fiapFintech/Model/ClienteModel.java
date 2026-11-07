package com.rickdev.fiapFintech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "T_FIN_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FIN_CLIENTE")
    @SequenceGenerator(name = "SQ_FIN_CLIENTE", sequenceName = "SQ_FIN_CLIENTE", allocationSize = 1)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nm_cliente", nullable = false, length = 100)
    private String nome;

    @Column(name = "ds_email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "ds_cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
}