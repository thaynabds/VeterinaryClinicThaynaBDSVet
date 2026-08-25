package services;

import java.util.List;
import models.Animal;
import models.Tutor;
import repositories.AnimalRepository;
import repositories.TutorRepository;

public class AnimalService {
    private AnimalRepository repository = new AnimalRepository();
    private TutorRepository tutorRepository = new TutorRepository();

    public boolean validar(Animal animal) {
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            System.out.println("ERRO: nome do animal e obrigatorio.");
            return false;
        }
        if (animal.getDocumentoTutor() == null || animal.getDocumentoTutor().trim().isEmpty()) {
            System.out.println("ERRO: documento do tutor e obrigatorio.");
            return false;
        }
        if (animal.getEspecie() == null || animal.getEspecie().trim().isEmpty()) {
            System.out.println("ERRO: especie do animal e obrigatoria.");
            return false;
        }
        return true;
    }

    public Animal cadastrar(Animal animal) {
        if (!validar(animal)) {
            return null;
        }
        return repository.create(animal);
    }

    public List<Animal> listar() {
        return repository.findAll();
    }

    public Animal buscarPorId(long id) {
        return repository.findById(id);
    }

    public List<Animal> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Animal> buscarPorEspecie(String especie) {
        return repository.findByEspecie(especie);
    }

    public List<Animal> buscarPorDocumentoTutor(String documento) {
        return repository.findByDocumentoTutor(documento);
    }

    public boolean atualizar(long id, Animal novo) {
        if (!validar(novo)) {
            return false;
        }
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        Animal animal = repository.findById(id);
        if (animal == null) {
            return false;
        }

        List<Tutor> tutores = tutorRepository.findAll();
        for (int i = 0; i < tutores.size(); i++) {
            Tutor tutor = tutores.get(i);
            tutor.getAnimais().remove(animal);
        }

        return repository.delete(id);
    }
}
