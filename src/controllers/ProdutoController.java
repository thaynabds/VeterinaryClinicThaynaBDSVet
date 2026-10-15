package controllers;

import java.util.List;
import java.util.Scanner;
import models.Produto;
import services.RegraNegocioException;
import services.ProdutoService;

public class ProdutoController {
    private ProdutoService service = new ProdutoService();
    private Scanner scanner;

    public ProdutoController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- Cadastro de Produto ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Valor: R$ ");
        double valor = lerDouble();
        System.out.print("Quantidade: ");
        long quantidade = lerLong();
        System.out.print("Setor: ");
        String setor = scanner.nextLine();

        try {
            Produto salvo = service.cadastrar(new Produto(nome, valor, quantidade, setor));
            System.out.println("Produto cadastrado! ID: " + salvo.getId());
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void buscarPorId() {
        System.out.print("\nID do produto: ");
        long id = lerLong();
        Produto produto = service.buscarPorId(id);
        if (produto == null) {
            System.out.println("Produto nao encontrado.");
        } else {
            System.out.println(produto);
        }
    }

    public void buscarPorNome() {
        System.out.print("\nNome do produto: ");
        String nome = scanner.nextLine();
        List<Produto> lista = service.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void buscarPorSetor() {
        System.out.print("\nSetor do produto: ");
        String setor = scanner.nextLine();
        List<Produto> lista = service.buscarPorSetor(setor);
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado nesse setor.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void buscarPorFaixaDeValor() {
        System.out.print("\nValor minimo: R$ ");
        double min = lerDouble();
        System.out.print("Valor maximo: R$ ");
        double max = lerDouble();
        List<Produto> lista;
        try {
            lista = service.buscarPorFaixaDeValor(min, max);
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto nessa faixa de valor.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void atualizar() {
        System.out.print("\nID do produto a atualizar: ");
        long id = lerLong();
        Produto existente = service.buscarPorId(id);
        if (existente == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo valor: R$ ");
        double valor = lerDouble();
        System.out.print("Nova quantidade: ");
        long quantidade = lerLong();
        System.out.print("Novo setor: ");
        String setor = scanner.nextLine();

        try {
            if (service.atualizar(id, new Produto(id, nome, valor, quantidade, setor))) {
                System.out.println("Produto atualizado!");
            } else {
                System.out.println("Falha ao atualizar.");
            }
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar() {
        System.out.print("\nID do produto a deletar: ");
        long id = lerLong();
        if (service.deletar(id)) {
            System.out.println("Produto removido!");
        } else {
            System.out.println("Produto nao encontrado.");
        }
    }

    private double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor valido: ");
            }
        }
    }

    private long lerLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero valido: ");
            }
        }
    }
}
