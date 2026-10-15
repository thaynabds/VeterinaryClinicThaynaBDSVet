package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Tutor;

public class TutorRepository {
    private static List<Tutor> tutores = new ArrayList<Tutor>();

    public Tutor create(Tutor tutor) {
        tutores.add(tutor);
        return tutor;
    }

    public List<Tutor> findAll() {
        return new ArrayList<Tutor>(tutores);
    }

    public Tutor findById(long id) {
        for (int i = 0; i < tutores.size(); i++) {
            Tutor tutor = tutores.get(i);
            if (tutor.getId() == id) {
                return tutor;
            }
        }
        return null;
    }

    public List<Tutor> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<Tutor> resultado = new ArrayList<Tutor>();
        for (int i = 0; i < tutores.size(); i++) {
            Tutor tutor = tutores.get(i);
            if (tutor.getNomeCompleto() != null
                    && tutor.getNomeCompleto().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(tutor);
            }
        }
        return resultado;
    }

    public Tutor findByDocumento(String documento) {
        for (int i = 0; i < tutores.size(); i++) {
            Tutor tutor = tutores.get(i);
            if (tutor.getDocumento() != null && tutor.getDocumento().equals(documento)) {
                return tutor;
            }
        }
        return null;
    }

    public boolean update(long id, Tutor novo) {
        Tutor existente = findById(id);
        if (existente == null) {
            return false;
        }
        existente.setNomeCompleto(novo.getNomeCompleto());
        existente.setDocumento(novo.getDocumento());
        existente.setEndereco(novo.getEndereco());
        return true;
    }

    public boolean delete(long id) {
        Tutor existente = findById(id);
        if (existente == null) {
            return false;
        }
        tutores.remove(existente);
        return true;
    }

    public static void limpar() {
        tutores.clear();
    }

}
