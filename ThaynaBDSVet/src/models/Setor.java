package models;

public class Setor {
    private static long contador = 0;

    private long id;
    private String nome;
    private String descricao;

    public Setor() {
    }

    public Setor(String nome, String descricao) {
        this.id = ++contador;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Setor(long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        return "Setor #" + id + " | " + nome + " | " + descricao;
    }
}
