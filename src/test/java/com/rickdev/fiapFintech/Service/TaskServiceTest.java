package com.rickdev.fiapFintech.Service;

import com.rickdev.fiapFintech.Dto.TaskDto;
import com.rickdev.fiapFintech.Model.ClienteModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


    @Test
    @DisplayName("Should salvar task successfully")
    void salvar() {
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        ClienteModel task = new ClienteModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.save(any(ClienteModel.class))).thenReturn(task);

        ClienteModel taskSalvas = taskService.salvar(dto);

        assertNotNull(taskSalvas);
        assertEquals("Limpeza", taskSalvas.getTitulo());

        verify(taskRepository, times(1)).save(any(ClienteModel.class));
    }

    @Test
    @DisplayName("Should return a list of tasks successfully")
    void listar() {
        ClienteModel task1 = new ClienteModel();
        task1.setTitulo("Limpeza");
        task1.setDescricao("Realizar limpeza de perifericos");
        task1.setDataCriacao(LocalDateTime.now());
        task1.setStatus(StatusEnum.A_FAZER);

        ClienteModel task2 = new ClienteModel();
        task2.setTitulo("Manutencao");
        task2.setDescricao("Realizar manutencao de perifericos");
        task2.setDataCriacao(LocalDateTime.now());
        task2.setStatus(StatusEnum.A_FAZER);

        List<ClienteModel> listaMocks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(listaMocks);

        List<ClienteModel> tarefas = taskService.listarTodos();

        //deve testar o retorno da service
        assertNotNull(tarefas);
        assertEquals("Limpeza", tarefas.get(0).getTitulo());
        assertEquals("Manutencao", tarefas.get(1).getTitulo());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return sucess finding taskById")
    void atualizarWithSucees() {
        Long id = 1L;
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        ClienteModel task = new ClienteModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskRepository.saveAndFlush(any(ClienteModel.class))).thenReturn(task);

        ClienteModel taskAtualizada = taskService.atualizarPorId(id, dto);

        assertNotNull(taskAtualizada);
        assertEquals("Limpeza", taskAtualizada.getTitulo());

        verify(taskRepository, times(1)).saveAndFlush(any(ClienteModel.class));
    }

    @Test
    @DisplayName("Should return exception when id is invalid")
    void atualizarWithException() {
        Long id = 1L;
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        ClienteModel task = new ClienteModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, (() -> {
            taskService.atualizarPorId(id, dto);
        }));

        assertEquals("Task Nao existente", exception.getMessage());

        verify(taskRepository, never()).saveAndFlush(any(ClienteModel.class));

    }

    @Test
    @DisplayName("Should return sucess finding task by id")
    void findWithSuccess() {

        Long id = 1L;
        ClienteModel task = new ClienteModel();
        task.setTitulo("Manutencao");
        task.setDescricao("Realizar manutencao de perifericos");
        task.setDataCriacao(LocalDateTime.now());
        task.setStatus(StatusEnum.A_FAZER);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        ClienteModel taskEncontrada = taskService.acharPorId(id);

        assertNotNull(taskEncontrada);
        assertEquals("Manutencao", taskEncontrada.getTitulo());

        verify(taskRepository, times(1)).findById(id);

    }

    @Test
    @DisplayName("Should return a exception with a invalid id")
    void findWithException() {
        Long id = 1L;
        ClienteModel task = new ClienteModel();
        task.setTitulo("Manutencao");
        task.setDescricao("Realizar manutencao de perifericos");
        task.setDataCriacao(LocalDateTime.now());
        task.setStatus(StatusEnum.A_FAZER);

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, (()->
        {taskService.acharPorId(id);})
        );

        assertEquals("Id nao encontrado na base de dados", exception.getMessage());

        verify(taskRepository, times(1)).findById(id);

    }

    @Test
    @DisplayName("Should return sucess when delete a task with a valid id")
    void deleteWithSucess() {

        Long id = 1L;
        //nao tem necessidade de instanciar model
        when(taskRepository.existsById(id)).thenReturn(true);
        taskService.deletarPorId(id);
        verify(taskRepository, times(1)).deleteById(id);

    }

    @Test
    @DisplayName("Should return a exception with a invalid id")
    void deleteWithException() {
        Long id = 1L;
        when(taskRepository.existsById(id)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {taskService.deletarPorId(id);});
        assertEquals("Task nao existe", exception.getMessage());
        verify(taskRepository, never()).deleteById(id);

    }

}