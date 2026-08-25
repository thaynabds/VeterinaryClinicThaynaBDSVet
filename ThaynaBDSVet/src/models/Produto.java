package models;

public class Produto {
    private static long contador = 0;

    private long id;
    private String nome;
    private double valor;
    private long quantidade;
    private String setor;

    public Produto() {
    }

    public Produto(String nome, double valor, long quantidade, String setor) {
        this.id = ++contador;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.setor = setor;
    }

    public Produto(long id, String nome, double valor, long quantidade, String setor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.setor = setor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String toString() {
        return "Produto #" + id + " | " + nome
                + " | R$ " + String.format("%.2f", valor)
                + " | Qtd: " + quantidade
                + " | Setor: " + setor;
    }
}
