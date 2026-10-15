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
        return new ArrayList<Setor>(setores);
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
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
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

    public Setor findByNomeExato(String nome) {
        if (nome == null) {
            return null;
        }
        for (int i = 0; i < setores.size(); i++) {
            Setor setor = setores.get(i);
            if (setor.getNome() != null && setor.getNome().equalsIgnoreCase(nome.trim())) {
                return setor;
            }
        }
        return null;
    }

    public static void limpar() {
        setores.clear();
    }

}
