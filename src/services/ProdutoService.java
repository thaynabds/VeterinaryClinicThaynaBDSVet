package services;

import java.util.List;
import models.Produto;
import repositories.ProdutoRepository;

public class ProdutoService {
    private ProdutoRepository repository = new ProdutoRepository();

    public Produto cadastrar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            System.out.println("ERRO: nome do produto e obrigatorio.");
            return null;
        }
        if (produto.getValor() < 0) {
            System.out.println("ERRO: valor nao pode ser negativo.");
            return null;
        }
        if (produto.getQuantidade() < 0) {
            System.out.println("ERRO: quantidade nao pode ser negativa.");
            return null;
        }
        return repository.create(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(long id) {
        return repository.findById(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Produto> buscarPorSetor(String setor) {
        return repository.findBySetor(setor);
    }

    public List<Produto> buscarPorFaixaDeValor(double min, double max) {
        if (min < 0 || max < 0) {
            System.out.println("ERRO: valores da faixa nao podem ser negativos.");
            return null;
        }
        if (min > max) {
            System.out.println("ERRO: valor minimo nao pode ser maior que o maximo.");
            return null;
        }
        return repository.findByFaixaDeValor(min, max);
    }

    public boolean atualizar(long id, Produto novo) {
        if (novo.getNome() == null || novo.getNome().trim().isEmpty()) {
            System.out.println("ERRO: nome do produto e obrigatorio.");
            return false;
        }
        if (novo.getValor() < 0) {
            System.out.println("ERRO: valor nao pode ser negativo.");
            return false;
        }
        if (novo.getQuantidade() < 0) {
            System.out.println("ERRO: quantidade nao pode ser negativa.");
            return false;
        }
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        return repository.delete(id);
    }
}
