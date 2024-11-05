package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepo;

    // Listar todas as tarefas
    public List<TarefaDTO> getAll() {
        return tarefaRepo.findAll().stream()
                .map(TarefaDTO::new)
                .toList();
    }

    // Adicionar uma nova tarefa
    public TarefaDTO insert(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);
        return new TarefaDTO(tarefaRepo.save(tarefa));
    }

    // Editar uma tarefa existente
    public TarefaDTO update(long id, TarefaDTO tarefaDTO) {
        Optional<Tarefa> optionalTarefa = tarefaRepo.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setTitulo(tarefaDTO.titulo());
            tarefa.setDescricao(tarefaDTO.descricao());
            tarefa.setDataInicio(tarefaDTO.dataInicio());
            tarefa.setDataConclusao(tarefaDTO.dataConclusao());
            // Atualiza outros campos se necessário
            return new TarefaDTO(tarefaRepo.save(tarefa));
        }
        return null; // ou lançar uma exceção se preferir
    }

    // Excluir uma tarefa pelo ID
    public void delete(long id) {
        tarefaRepo.deleteById(id);
    }

    // Buscar tarefa por ID
    public Optional<TarefaDTO> getById(long id) {
        return tarefaRepo.findById(id).map(TarefaDTO::new);
    }
}
