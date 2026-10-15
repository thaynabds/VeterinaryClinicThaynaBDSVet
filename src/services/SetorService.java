package services;

import java.util.List;
import models.Setor;
import repositories.ProdutoRepository;
import repositories.SetorRepository;

public class SetorService {
    private SetorRepository repository = new SetorRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    public Setor cadastrar(Setor setor) {
        validar(setor);
        if (repository.findByNomeExato(setor.getNome()) != null) {
            throw new RegraNegocioException("Ja existe setor com esse nome.");
        }
        return repository.create(setor);
    }

    public List<Setor> listar() {
        return repository.findAll();
    }

    public Setor buscarPorId(long id) {
        return repository.findById(id);
    }

    public List<Setor> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public boolean atualizar(long id, Setor novo) {
        validar(novo);
        Setor outro = repository.findByNomeExato(novo.getNome());
        if (outro != null && outro.getId() != id) {
            throw new RegraNegocioException("Ja existe setor com esse nome.");
        }
        Setor atual = repository.findById(id);
        if (atual == null) {
            return false;
        }
        String nomeAntigo = atual.getNome();
        boolean ok = repository.update(id, novo);
        if (ok && !nomeAntigo.equals(novo.getNome())) {
            produtoRepository.renomearSetor(nomeAntigo, novo.getNome());
        }
        return ok;
    }

    public boolean deletar(long id) {
        Setor setor = repository.findById(id);
        if (setor == null) {
            return false;
        }
        if (produtoRepository.contarPorSetor(setor.getNome()) > 0) {
            throw new RegraNegocioException(
                    "Nao e possivel apagar o setor enquanto houver produto nele.");
        }
        return repository.delete(id);
    }

    private void validar(Setor setor) {
        if (setor.getNome() == null || setor.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do setor e obrigatorio.");
        }
        setor.setNome(setor.getNome().trim());
    }
}
