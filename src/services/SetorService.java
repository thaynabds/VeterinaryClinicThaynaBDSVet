package services;

import java.util.List;
import models.Setor;
import repositories.SetorRepository;

public class SetorService {
    private SetorRepository repository = new SetorRepository();

    public Setor cadastrar(Setor setor) {
        if (setor.getNome() == null || setor.getNome().trim().isEmpty()) {
            System.out.println("ERRO: nome do setor e obrigatorio.");
            return null;
        }
        return repository.create(setor);
    }

    public List<Setor> listar() {
        return repository.findAll();
    }

    public Setor buscarPorId(long id) {
        return repository.findById(id);
    }

    public List<Setor> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public boolean atualizar(long id, Setor novo) {
        if (novo.getNome() == null || novo.getNome().trim().isEmpty()) {
            System.out.println("ERRO: nome do setor e obrigatorio.");
            return false;
        }
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        return repository.delete(id);
    }
}
