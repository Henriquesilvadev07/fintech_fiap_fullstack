package com.rickdev.fiapFintech.Repository;

import com.rickdev.fiapFintech.Model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<ClienteModel, Long> {
}
