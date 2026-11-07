package com.rickdev.fiapFintech.Repository;

import com.rickdev.fiapFintech.Model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaModel, Long> {
}
