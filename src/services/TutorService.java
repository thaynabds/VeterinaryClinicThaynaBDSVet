package services;

import java.util.List;
import models.Animal;
import models.Tutor;
import repositories.TutorRepository;

public class TutorService {
    private TutorRepository repository = new TutorRepository();

    public Tutor cadastrar(Tutor tutor) {
        validar(tutor);
        if (repository.findByDocumento(tutor.getDocumento()) != null) {
            throw new RegraNegocioException("Ja existe tutor com esse documento.");
        }
        return repository.create(tutor);
    }

    public List<Tutor> listar() {
        return repository.findAll();
    }

    public Tutor buscarPorId(long id) {
        return repository.findById(id);
    }

    public List<Tutor> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public Tutor buscarPorDocumento(String documento) {
        if (documento == null) {
            return null;
        }
        return repository.findByDocumento(documento.trim());
    }

    public boolean atualizar(long id, Tutor novo) {
        validar(novo);
        Tutor outro = repository.findByDocumento(novo.getDocumento());
        if (outro != null && outro.getId() != id) {
            throw new RegraNegocioException("Ja existe tutor com esse documento.");
        }
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        Tutor tutor = repository.findById(id);
        if (tutor == null) {
            return false;
        }
        if (!tutor.getAnimais().isEmpty()) {
            throw new RegraNegocioException(
                    "Nao e possivel apagar o tutor enquanto houver animal na ficha.");
        }
        return repository.delete(id);
    }

    public boolean adicionarAnimal(long idTutor, Animal animal) {
        Tutor tutor = repository.findById(idTutor);
        if (tutor == null || animal == null) {
            return false;
        }

        boolean jaExiste = false;
        for (int i = 0; i < tutor.getAnimais().size(); i++) {
            if (tutor.getAnimais().get(i).getId() == animal.getId()) {
                jaExiste = true;
            }
        }
        if (!jaExiste) {
            tutor.getAnimais().add(animal);
        }

        boolean tutorJaVinculado = false;
        for (int i = 0; i < animal.getIdsTutores().size(); i++) {
            if (animal.getIdsTutores().get(i).longValue() == idTutor) {
                tutorJaVinculado = true;
            }
        }
        if (!tutorJaVinculado) {
            animal.getIdsTutores().add(Long.valueOf(idTutor));
        }
        return true;
    }

    public boolean adicionarContato(long idTutor, String contato) {
        Tutor tutor = repository.findById(idTutor);
        if (tutor == null) {
            return false;
        }
        if (contato == null || contato.trim().isEmpty()) {
            throw new RegraNegocioException("Contato nao pode ser vazio.");
        }
        tutor.getContatos().add(contato.trim());
        return true;
    }

    private void validar(Tutor tutor) {
        if (tutor.getNomeCompleto() == null || tutor.getNomeCompleto().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do tutor e obrigatorio.");
        }
        if (tutor.getDocumento() == null || tutor.getDocumento().trim().isEmpty()) {
            throw new RegraNegocioException("Documento do tutor e obrigatorio.");
        }
        tutor.setNomeCompleto(tutor.getNomeCompleto().trim());
        tutor.setDocumento(tutor.getDocumento().trim());
    }
}
