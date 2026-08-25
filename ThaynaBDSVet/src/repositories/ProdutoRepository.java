package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Produto;

public class ProdutoRepository {
    private static List<Produto> produtos = new ArrayList<Produto>();

    public Produto create(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    public List<Produto> findAll() {
        return produtos;
    }

    public Produto findById(long id) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public List<Produto> findByNome(String nome) {
        List<Produto> resultado = new ArrayList<Produto>();
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getNome() != null
                    && produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> findBySetor(String setor) {
        List<Produto> resultado = new ArrayList<Produto>();
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getSetor() != null
                    && produto.getSetor().toLowerCase().contains(setor.toLowerCase())) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> findByFaixaDeValor(double min, double max) {
        List<Produto> resultado = new ArrayList<Produto>();
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getValor() >= min && produto.getValor() <= max) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public boolean update(long id, Produto novo) {
        Produto existente = findById(id);
        if (existente == null) {
            return false;
        }
        existente.setNome(novo.getNome());
        existente.setValor(novo.getValor());
        existente.setQuantidade(novo.getQuantidade());
        existente.setSetor(novo.getSetor());
        return true;
    }

    public boolean delete(long id) {
        Produto existente = findById(id);
        if (existente == null) {
            return false;
        }
        produtos.remove(existente);
        return true;
    }
}
