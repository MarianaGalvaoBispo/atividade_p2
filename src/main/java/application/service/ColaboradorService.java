package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.model.Colaborador;
import application.record.ColaboradorDTO;
import application.repository.ColaboradorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    // Listar todos os colaboradores
    public List<ColaboradorDTO> getAll() {
        return colaboradorRepo.findAll().stream()
                .map(ColaboradorDTO::new)
                .toList();
    }

    // Adicionar um novo colaborador
    public ColaboradorDTO insert(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador(colaboradorDTO);
        return new ColaboradorDTO(colaboradorRepo.save(colaborador));
    }

    // Editar um colaborador existente
    public ColaboradorDTO update(long id, ColaboradorDTO colaboradorDTO) {
        Optional<Colaborador> optionalColaborador = colaboradorRepo.findById(id);
        if (optionalColaborador.isPresent()) {
            Colaborador colaborador = optionalColaborador.get();
            colaborador.setNome(colaboradorDTO.nome());
            // Atualize outros campos se necessário
            return new ColaboradorDTO(colaboradorRepo.save(colaborador));
        }
        return null; // ou lançar uma exceção se preferir
    }

    // Excluir um colaborador pelo ID
    public void delete(long id) {
        colaboradorRepo.deleteById(id);
    }

    // Buscar colaborador por ID
    public Optional<ColaboradorDTO> getById(long id) {
        return colaboradorRepo.findById(id).map(ColaboradorDTO::new);
    }
}
