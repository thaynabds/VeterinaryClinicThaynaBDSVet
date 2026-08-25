package models;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private static long contador = 0;

    private long id;
    private String nome;
    private String documentoTutor;
    private String especie;
    private String raca;
    private String sexo;
    private String pelagem;
    private String idade;
    private String historicoVacinas;
    private String alergias;
    private String condicoesSaude;
    private boolean castrado;
    private String observacoes;
    private String anotacoes;
    private List<Long> idsTutores;

    public Animal() {
        this.idsTutores = new ArrayList<Long>();
    }

    public Animal(String nome, String documentoTutor, String especie) {
        this.id = ++contador;
        this.nome = nome;
        this.documentoTutor = documentoTutor;
        this.especie = especie;
        this.castrado = false;
        this.idsTutores = new ArrayList<Long>();
    }

    public Animal(long id, String nome, String documentoTutor, String especie,
            String raca, String sexo, String pelagem, String idade,
            String historicoVacinas, String alergias, String condicoesSaude,
            boolean castrado, String observacoes, String anotacoes) {
        this.id = id;
        this.nome = nome;
        this.documentoTutor = documentoTutor;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.pelagem = pelagem;
        this.idade = idade;
        this.historicoVacinas = historicoVacinas;
        this.alergias = alergias;
        this.condicoesSaude = condicoesSaude;
        this.castrado = castrado;
        this.observacoes = observacoes;
        this.anotacoes = anotacoes;
        this.idsTutores = new ArrayList<Long>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoTutor() {
        return documentoTutor;
    }

    public void setDocumentoTutor(String documentoTutor) {
        this.documentoTutor = documentoTutor;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getHistoricoVacinas() {
        return historicoVacinas;
    }

    public void setHistoricoVacinas(String historicoVacinas) {
        this.historicoVacinas = historicoVacinas;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicoesSaude() {
        return condicoesSaude;
    }

    public void setCondicoesSaude(String condicoesSaude) {
        this.condicoesSaude = condicoesSaude;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public List<Long> getIdsTutores() {
        return idsTutores;
    }

    public void setIdsTutores(List<Long> idsTutores) {
        this.idsTutores = idsTutores;
    }

    public String toString() {
        String textoCastrado;
        if (castrado) {
            textoCastrado = "Sim";
        } else {
            textoCastrado = "Nao";
        }

        return "Animal #" + id + " | " + nome
                + " | Especie: " + especie
                + " | Raca: " + raca
                + " | Sexo: " + sexo
                + " | Pelagem: " + pelagem
                + " | Idade: " + idade
                + " | Doc Tutor: " + documentoTutor
                + " | Tutores (IDs): " + idsTutores
                + " | Castrado: " + textoCastrado
                + "\n   Vacinas: " + historicoVacinas
                + "\n   Alergias: " + alergias
                + "\n   Condicoes de saude: " + condicoesSaude
                + "\n   Observacoes: " + observacoes
                + "\n   Anotacoes: " + anotacoes;
    }
}
