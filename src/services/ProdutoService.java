package services;

import java.util.List;
import models.Produto;
import models.Setor;
import repositories.ProdutoRepository;
import repositories.SetorRepository;

public class ProdutoService {
    private ProdutoRepository repository = new ProdutoRepository();
    private SetorRepository setorRepository = new SetorRepository();

    public Produto cadastrar(Produto produto) {
        validar(produto);
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
            throw new RegraNegocioException("Valores da faixa nao podem ser negativos.");
        }
        if (min > max) {
            throw new RegraNegocioException("Valor minimo nao pode ser maior que o maximo.");
        }
        return repository.findByFaixaDeValor(min, max);
    }

    public boolean atualizar(long id, Produto novo) {
        validar(novo);
        return repository.update(id, novo);
    }

    public boolean deletar(long id) {
        return repository.delete(id);
    }

    private void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do produto e obrigatorio.");
        }
        if (produto.getValor() < 0) {
            throw new RegraNegocioException("Valor nao pode ser negativo.");
        }
        if (produto.getQuantidade() < 0) {
            throw new RegraNegocioException("Quantidade nao pode ser negativa.");
        }
        if (produto.getSetor() == null || produto.getSetor().trim().isEmpty()) {
            throw new RegraNegocioException("Setor do produto e obrigatorio.");
        }
        Setor setor = setorRepository.findByNomeExato(produto.getSetor());
        if (setor == null) {
            throw new RegraNegocioException("Setor nao cadastrado.");
        }
        produto.setNome(produto.getNome().trim());
        produto.setSetor(setor.getNome());
    }
}
