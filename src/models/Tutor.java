package models;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private static long contador = 0;

    private long id;
    private String nomeCompleto;
    private String documento;
    private String endereco;
    private List<String> contatos;
    private List<Animal> animais;

    public Tutor() {
        this.contatos = new ArrayList<String>();
        this.animais = new ArrayList<Animal>();
    }

    public Tutor(String nomeCompleto, String documento, String endereco) {
        this.id = ++contador;
        this.nomeCompleto = nomeCompleto;
        this.documento = documento;
        this.endereco = endereco;
        this.contatos = new ArrayList<String>();
        this.animais = new ArrayList<Animal>();
    }

    public Tutor(long id, String nomeCompleto, String documento, String endereco) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.documento = documento;
        this.endereco = endereco;
        this.contatos = new ArrayList<String>();
        this.animais = new ArrayList<Animal>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<String> getContatos() {
        return contatos;
    }

    public void setContatos(List<String> contatos) {
        this.contatos = contatos;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public String toString() {
        return "Tutor #" + id + " | " + nomeCompleto
                + " | Doc: " + documento
                + " | Endereco: " + endereco
                + " | Contatos: " + contatos
                + " | Qtd animais: " + animais.size();
    }
}
