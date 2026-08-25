package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Animal;

public class AnimalRepository {
    private static List<Animal> animais = new ArrayList<Animal>();

    public Animal create(Animal animal) {
        animais.add(animal);
        return animal;
    }

    public List<Animal> findAll() {
        return animais;
    }

    public Animal findById(long id) {
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    public List<Animal> findByNome(String nome) {
        List<Animal> resultado = new ArrayList<Animal>();
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            if (animal.getNome() != null
                    && animal.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(animal);
            }
        }
        return resultado;
    }

    public List<Animal> findByEspecie(String especie) {
        List<Animal> resultado = new ArrayList<Animal>();
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            if (animal.getEspecie() != null
                    && animal.getEspecie().toLowerCase().contains(especie.toLowerCase())) {
                resultado.add(animal);
            }
        }
        return resultado;
    }

    public List<Animal> findByDocumentoTutor(String documentoTutor) {
        List<Animal> resultado = new ArrayList<Animal>();
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            if (animal.getDocumentoTutor() != null
                    && animal.getDocumentoTutor().equals(documentoTutor)) {
                resultado.add(animal);
            }
        }
        return resultado;
    }

    public boolean update(long id, Animal novo) {
        Animal existente = findById(id);
        if (existente == null) {
            return false;
        }
        existente.setNome(novo.getNome());
        existente.setDocumentoTutor(novo.getDocumentoTutor());
        existente.setEspecie(novo.getEspecie());
        existente.setRaca(novo.getRaca());
        existente.setSexo(novo.getSexo());
        existente.setPelagem(novo.getPelagem());
        existente.setIdade(novo.getIdade());
        existente.setHistoricoVacinas(novo.getHistoricoVacinas());
        existente.setAlergias(novo.getAlergias());
        existente.setCondicoesSaude(novo.getCondicoesSaude());
        existente.setCastrado(novo.isCastrado());
        existente.setObservacoes(novo.getObservacoes());
        existente.setAnotacoes(novo.getAnotacoes());
        return true;
    }

    public boolean delete(long id) {
        Animal existente = findById(id);
        if (existente == null) {
            return false;
        }
        animais.remove(existente);
        return true;
    }
}
