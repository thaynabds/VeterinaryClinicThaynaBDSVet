package services;

import java.util.List;
import models.Animal;
import models.Tutor;
import repositories.AnimalRepository;
import repositories.TutorRepository;

public class AnimalService {
    private AnimalRepository repository = new AnimalRepository();
    private TutorRepository tutorRepository = new TutorRepository();

    public Animal cadastrar(Animal animal) {
        validar(animal);
        Tutor tutor = exigirTutor(animal.getDocumentoTutor());
        Animal salvo = repository.create(animal);
        new TutorService().adicionarAnimal(tutor.getId(), salvo);
        return salvo;
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
        validar(novo);
        Tutor tutor = exigirTutor(novo.getDocumentoTutor());
        if (!repository.update(id, novo)) {
            return false;
        }
        Animal salvo = repository.findById(id);
        new TutorService().adicionarAnimal(tutor.getId(), salvo);
        return true;
    }

    public boolean deletar(long id) {
        Animal animal = repository.findById(id);
        if (animal == null) {
            return false;
        }
        List<Tutor> tutores = tutorRepository.findAll();
        for (int i = 0; i < tutores.size(); i++) {
            tutores.get(i).getAnimais().remove(animal);
        }
        return repository.delete(id);
    }

    private void validar(Animal animal) {
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do animal e obrigatorio.");
        }
        if (animal.getDocumentoTutor() == null || animal.getDocumentoTutor().trim().isEmpty()) {
            throw new RegraNegocioException("Documento do tutor e obrigatorio.");
        }
        if (animal.getEspecie() == null || animal.getEspecie().trim().isEmpty()) {
            throw new RegraNegocioException("Especie do animal e obrigatoria.");
        }
        animal.setNome(animal.getNome().trim());
        animal.setDocumentoTutor(animal.getDocumentoTutor().trim());
        animal.setEspecie(animal.getEspecie().trim());
    }

    private Tutor exigirTutor(String documento) {
        Tutor tutor = tutorRepository.findByDocumento(documento);
        if (tutor == null) {
            throw new RegraNegocioException(
                    "Nao existe tutor com esse documento. Cadastre o tutor antes do animal.");
        }
        return tutor;
    }
}
