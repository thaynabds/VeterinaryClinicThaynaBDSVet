package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Setor;

public class SetorRepository {
    private static List<Setor> setores = new ArrayList<Setor>();

    public Setor create(Setor setor) {
        setores.add(setor);
        return setor;
    }

    public List<Setor> findAll() {
        return setores;
    }

    public Setor findById(long id) {
        for (int i = 0; i < setores.size(); i++) {
            Setor setor = setores.get(i);
            if (setor.getId() == id) {
                return setor;
            }
        }
        return null;
    }

    public List<Setor> findByNome(String nome) {
        List<Setor> resultado = new ArrayList<Setor>();
        for (int i = 0; i < setores.size(); i++) {
            Setor setor = setores.get(i);
            if (setor.getNome() != null
                    && setor.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(setor);
            }
        }
        return resultado;
    }

    public boolean update(long id, Setor novo) {
        Setor existente = findById(id);
        if (existente == null) {
            return false;
        }
        existente.setNome(novo.getNome());
        existente.setDescricao(novo.getDescricao());
        return true;
    }

    public boolean delete(long id) {
        Setor existente = findById(id);
        if (existente == null) {
            return false;
        }
        setores.remove(existente);
        return true;
    }
}
