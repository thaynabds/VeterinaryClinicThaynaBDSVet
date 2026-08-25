package services;

import java.util.List;
import models.Animal;
import models.Tutor;
import repositories.TutorRepository;

public class TutorService {
    private TutorRepository repository = new TutorRepository();

    public Tutor cadastrar(Tutor tutor) {
        if (tutor.getNomeCompleto() == null || tutor.getNomeCompleto().trim().isEmpty()) {
            System.out.println("ERRO: nome do tutor e obrigatorio.");
            return null;
        }
        if (tutor.getDocumento() == null || tutor.getDocumento().trim().isEmpty()) {
            System.out.println("ERRO: documento do tutor e obrigatorio.");
            return null;
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
        return repository.findByDocumento(documento);
    }

    public boolean atualizar(long id, Tutor novo) {
        if (novo.getNomeCompleto() == null || novo.getNomeCompleto().trim().isEmpty()) {
            System.out.println("ERRO: nome do tutor e obrigatorio.");
            return false;
        }
        if (novo.getDocumento() == null || novo.getDocumento().trim().isEmpty()) {
            System.out.println("ERRO: documento do tutor e obrigatorio.");
            return false;
        }
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        return repository.delete(id);
    }

    public boolean adicionarAnimal(long idTutor, Animal animal) {
        Tutor tutor = repository.findById(idTutor);
        if (tutor == null) {
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
            animal.getIdsTutores().add(idTutor);
        }

        return true;
    }

    public boolean adicionarContato(long idTutor, String contato) {
        Tutor tutor = repository.findById(idTutor);
        if (tutor == null) {
            return false;
        }
        if (contato == null || contato.trim().isEmpty()) {
            System.out.println("ERRO: contato nao pode ser vazio.");
            return false;
        }
        tutor.getContatos().add(contato);
        return true;
    }
}
