package com.rickdev.fiapFintech.Repository;

import com.rickdev.fiapFintech.Model.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {
}
