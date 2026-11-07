package com.rickdev.DevTasks.Repository;

import com.rickdev.DevTasks.Model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<ClienteModel, Long> {
}
