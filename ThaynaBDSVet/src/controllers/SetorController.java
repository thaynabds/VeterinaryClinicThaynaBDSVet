package controllers;

import java.util.List;
import java.util.Scanner;
import models.Setor;
import services.SetorService;

public class SetorController {
    private SetorService service = new SetorService();
    private Scanner scanner;

    public SetorController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- Cadastro de Setor ---");
        System.out.print("Nome do setor: ");
        String nome = scanner.nextLine();
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        Setor salvo = service.cadastrar(new Setor(nome, descricao));
        if (salvo != null) {
            System.out.println("Setor cadastrado! ID: " + salvo.getId());
        }
    }

    public void listar() {
        System.out.println("\n--- Lista de Setores ---");
        List<Setor> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum setor cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void buscarPorId() {
        System.out.print("\nID do setor: ");
        long id = lerLong();
        Setor setor = service.buscarPorId(id);
        if (setor == null) {
            System.out.println("Setor nao encontrado.");
        } else {
            System.out.println(setor);
        }
    }

    public void buscarPorNome() {
        System.out.print("\nNome do setor: ");
        String nome = scanner.nextLine();
        List<Setor> lista = service.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum setor encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void atualizar() {
        System.out.print("\nID do setor a atualizar: ");
        long id = lerLong();
        Setor existente = service.buscarPorId(id);
        if (existente == null) {
            System.out.println("Setor nao encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            nome = existente.getNome();
        }
        System.out.print("Nova descricao: ");
        String descricao = scanner.nextLine();
        if (descricao.trim().isEmpty()) {
            descricao = existente.getDescricao();
        }

        if (service.atualizar(id, new Setor(id, nome, descricao))) {
            System.out.println("Setor atualizado!");
        } else {
            System.out.println("Falha ao atualizar.");
        }
    }

    public void deletar() {
        System.out.print("\nID do setor a deletar: ");
        long id = lerLong();
        if (service.deletar(id)) {
            System.out.println("Setor removido!");
        } else {
            System.out.println("Setor nao encontrado.");
        }
    }

    public void carregarSetoresIniciais() {
        if (!service.listar().isEmpty()) {
            return;
        }
        service.cadastrar(new Setor("Recepcao e Caixa",
                "Contato inicial, pagamento e espera. Produtos de compra por impulso."));
        service.cadastrar(new Setor("Racoes, Petiscos e Suplementos",
                "Racoes secas e umidas, petiscos e suplementos por especie e idade."));
        service.cadastrar(new Setor("Higiene e Limpeza",
                "Areia sanitaria, tapetes higienicos, shampoos e eliminadores de odores."));
        service.cadastrar(new Setor("Acessorios e Conforto",
                "Coleiras, guias, caminhas, comedouros, roupas e brinquedos."));
        service.cadastrar(new Setor("Farmacia Pet",
                "Medicamentos, vermifugos, antipulgas e carrapaticidas."));
        service.cadastrar(new Setor("Banho e Tosa",
                "Setor tecnico de estetica e higiene dos pets."));
        service.cadastrar(new Setor("Consultorio Veterinario",
                "Sala para atendimento clinico, consultas e vacinacao."));
        service.cadastrar(new Setor("Estoque e Deposito",
                "Area interna para armazenamento e reposicao."));
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
