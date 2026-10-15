package controllers;

import java.util.List;
import java.util.Scanner;
import models.Animal;
import models.Tutor;
import services.RegraNegocioException;
import services.AnimalService;
import services.TutorService;

public class TutorController {
    private TutorService service = new TutorService();
    private AnimalService animalService = new AnimalService();
    private Scanner scanner;

    public TutorController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- Cadastro de Tutor ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("Documento (CPF/RG): ");
        String documento = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();

        Tutor tutor = new Tutor(nome, documento, endereco);

        System.out.print("Quantos contatos deseja cadastrar? ");
        int quantidade = lerInteiro();
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Contato " + (i + 1) + " (ex: WhatsApp: 99999-9999): ");
            tutor.getContatos().add(scanner.nextLine());
        }

        try {
            Tutor salvo = service.cadastrar(tutor);
            System.out.println("Tutor cadastrado! ID: " + salvo.getId());
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        System.out.println("\n--- Lista de Tutores ---");
        List<Tutor> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Tutor tutor = lista.get(i);
            System.out.println(tutor);
            if (!tutor.getAnimais().isEmpty()) {
                System.out.println("  Animais na ficha:");
                for (int j = 0; j < tutor.getAnimais().size(); j++) {
                    Animal animal = tutor.getAnimais().get(j);
                    System.out.println("    -> #" + animal.getId() + " " + animal.getNome()
                            + " (" + animal.getEspecie() + ")");
                }
            }
        }
    }

    public void buscarPorId() {
        System.out.print("\nID do tutor: ");
        long id = lerLong();
        Tutor tutor = service.buscarPorId(id);
        if (tutor == null) {
            System.out.println("Tutor nao encontrado.");
            return;
        }
        mostrarFicha(tutor);
    }

    public void buscarPorNome() {
        System.out.print("\nNome do tutor: ");
        String nome = scanner.nextLine();
        List<Tutor> lista = service.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum tutor encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void atualizar() {
        System.out.print("\nID do tutor a atualizar: ");
        long id = lerLong();
        Tutor existente = service.buscarPorId(id);
        if (existente == null) {
            System.out.println("Tutor nao encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.print("Novo nome: ");
        String nome = manterOuTrocar(existente.getNomeCompleto());
        System.out.print("Novo documento: ");
        String documento = manterOuTrocar(existente.getDocumento());
        System.out.print("Novo endereco: ");
        String endereco = manterOuTrocar(existente.getEndereco());

        Tutor novo = new Tutor(id, nome, documento, endereco);
        try {
            if (service.atualizar(id, novo)) {
                System.out.println("Tutor atualizado!");
            } else {
                System.out.println("Falha ao atualizar.");
            }
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarContato() {
        System.out.print("\nID do tutor: ");
        long id = lerLong();
        System.out.print("Novo contato (tipo livre, ex: WhatsApp: 99999-9999): ");
        String contato = scanner.nextLine();
        try {
            if (service.adicionarContato(id, contato)) {
                System.out.println("Contato adicionado!");
            } else {
                System.out.println("Tutor nao encontrado.");
            }
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar() {
        System.out.print("\nID do tutor a deletar: ");
        long id = lerLong();
        try {
            if (service.deletar(id)) {
                System.out.println("Tutor removido!");
            } else {
                System.out.println("Tutor nao encontrado.");
            }
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cadastrarAnimalNaFicha() {
        System.out.print("\nID do tutor para adicionar o animal: ");
        long idTutor = lerLong();
        Tutor tutor = service.buscarPorId(idTutor);
        if (tutor == null) {
            System.out.println("Tutor nao encontrado.");
            return;
        }

        System.out.println("--- Cadastro de Animal na ficha de " + tutor.getNomeCompleto() + " ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Especie (cao, gato, ave, outro): ");
        String especie = scanner.nextLine();

        Animal animal = new Animal(nome, tutor.getDocumento(), especie);
        System.out.print("Raca: ");
        animal.setRaca(scanner.nextLine());
        System.out.print("Sexo (Macho/Femea): ");
        animal.setSexo(scanner.nextLine());
        System.out.print("Pelagem / Cor: ");
        animal.setPelagem(scanner.nextLine());
        System.out.print("Idade / Data de nascimento: ");
        animal.setIdade(scanner.nextLine());
        System.out.print("Historico de vacinas: ");
        animal.setHistoricoVacinas(scanner.nextLine());
        System.out.print("Alergias e restricoes: ");
        animal.setAlergias(scanner.nextLine());
        System.out.print("Condicoes de saude / doencas: ");
        animal.setCondicoesSaude(scanner.nextLine());
        System.out.print("Castrado? (s/n): ");
        animal.setCastrado(scanner.nextLine().equalsIgnoreCase("s"));
        System.out.print("Observacoes sobre o animal: ");
        animal.setObservacoes(scanner.nextLine());
        System.out.print("Anotacoes (texto livre): ");
        animal.setAnotacoes(scanner.nextLine());

        Animal salvo = animalService.cadastrar(animal);
        if (salvo == null) {
            return;
        }

        service.adicionarAnimal(idTutor, salvo);
        System.out.println("Animal cadastrado na ficha do tutor! ID: " + salvo.getId());

        boolean continuar = true;
        while (continuar) {
            System.out.print("Deseja vincular este animal a outro tutor? (s/n): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                continuar = false;
            } else {
                System.out.print("ID do outro tutor: ");
                long outroId = lerLong();
                if (service.adicionarAnimal(outroId, salvo)) {
                    System.out.println("Animal tambem vinculado ao tutor " + outroId + "!");
                } else {
                    System.out.println("Outro tutor nao encontrado.");
                }
            }
        }
    }

    public void vincularAnimalExistente() {
        System.out.print("\nID do animal: ");
        long idAnimal = lerLong();
        Animal animal = animalService.buscarPorId(idAnimal);
        if (animal == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }
        System.out.print("ID do tutor: ");
        long idTutor = lerLong();
        if (service.adicionarAnimal(idTutor, animal)) {
            System.out.println("Animal vinculado ao tutor!");
        } else {
            System.out.println("Tutor nao encontrado.");
        }
    }

    private void mostrarFicha(Tutor tutor) {
        System.out.println("\n===== FICHA DO TUTOR =====");
        System.out.println(tutor);
        System.out.println("Contatos:");
        if (tutor.getContatos().isEmpty()) {
            System.out.println("  (nenhum contato)");
        } else {
            for (int i = 0; i < tutor.getContatos().size(); i++) {
                System.out.println("  - " + tutor.getContatos().get(i));
            }
        }
        System.out.println("Animais:");
        if (tutor.getAnimais().isEmpty()) {
            System.out.println("  (nenhum animal na ficha)");
        } else {
            for (int i = 0; i < tutor.getAnimais().size(); i++) {
                System.out.println(tutor.getAnimais().get(i));
                System.out.println("---");
            }
        }
    }

    private String manterOuTrocar(String atual) {
        String digitado = scanner.nextLine();
        if (digitado.trim().isEmpty()) {
            return atual;
        }
        return digitado;
    }

    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero valido: ");
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
