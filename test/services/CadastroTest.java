package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Animal;
import models.Produto;
import models.Setor;
import models.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.AnimalRepository;
import repositories.ProdutoRepository;
import repositories.SetorRepository;
import repositories.TutorRepository;

class CadastroTest {
    private TutorService tutores;
    private AnimalService animais;
    private SetorService setores;
    private ProdutoService produtos;

    @BeforeEach
    void limpar() {
        TutorRepository.limpar();
        AnimalRepository.limpar();
        SetorRepository.limpar();
        ProdutoRepository.limpar();
        tutores = new TutorService();
        animais = new AnimalService();
        setores = new SetorService();
        produtos = new ProdutoService();
    }

    @Test
    void tutorEntraNaLista() {
        Tutor salvo = tutores.cadastrar(new Tutor("Ana Lima", "111", "Recife"));
        assertEquals("Ana Lima", salvo.getNomeCompleto());
        assertEquals(1, tutores.listar().size());
    }

    @Test
    void recusaTutorSemNome() {
        assertThrows(RegraNegocioException.class,
                () -> tutores.cadastrar(new Tutor("  ", "111", "Recife")));
    }

    @Test
    void recusaDocumentoRepetido() {
        tutores.cadastrar(new Tutor("Ana Lima", "111", "Recife"));
        assertThrows(RegraNegocioException.class,
                () -> tutores.cadastrar(new Tutor("Outra Ana", "111", "Olinda")));
    }

    @Test
    void animalEntraNaFichaDoTutor() {
        Tutor tutor = tutores.cadastrar(new Tutor("Ana Lima", "111", "Recife"));
        Animal animal = animais.cadastrar(new Animal("Bidu", "111", "cao"));
        assertEquals(1, tutor.getAnimais().size());
        assertEquals("Bidu", tutor.getAnimais().get(0).getNome());
        assertTrue(animal.getIdsTutores().contains(tutor.getId()));
    }

    @Test
    void recusaAnimalSemTutor() {
        assertThrows(RegraNegocioException.class,
                () -> animais.cadastrar(new Animal("Bidu", "999", "cao")));
        assertEquals(0, animais.listar().size());
    }

    @Test
    void naoApagaTutorComAnimal() {
        tutores.cadastrar(new Tutor("Ana Lima", "111", "Recife"));
        animais.cadastrar(new Animal("Bidu", "111", "cao"));
        Tutor tutor = tutores.buscarPorDocumento("111");
        assertThrows(RegraNegocioException.class, () -> tutores.deletar(tutor.getId()));
        assertEquals(1, tutores.listar().size());
    }

    @Test
    void produtoExigeSetorExistente() {
        setores.cadastrar(new Setor("Farmacia Pet", "Medicamentos"));
        Produto salvo = produtos.cadastrar(new Produto("Antipulgas", 42.5, 3, "farmacia pet"));
        assertEquals("Farmacia Pet", salvo.getSetor());
        assertThrows(RegraNegocioException.class,
                () -> produtos.cadastrar(new Produto("Coleira", 10, 1, "Inexistente")));
    }

    @Test
    void recusaValorNegativo() {
        setores.cadastrar(new Setor("Farmacia Pet", "Medicamentos"));
        assertThrows(RegraNegocioException.class,
                () -> produtos.cadastrar(new Produto("Antipulgas", -1, 1, "Farmacia Pet")));
    }

    @Test
    void naoApagaSetorComProduto() {
        Setor setor = setores.cadastrar(new Setor("Farmacia Pet", "Medicamentos"));
        produtos.cadastrar(new Produto("Antipulgas", 42.5, 3, "Farmacia Pet"));
        assertThrows(RegraNegocioException.class, () -> setores.deletar(setor.getId()));
    }

    @Test
    void faixaInvertidaRecusada() {
        assertThrows(RegraNegocioException.class, () -> produtos.buscarPorFaixaDeValor(20, 10));
    }
}
