package controllers;

import java.util.List;
import java.util.Scanner;
import models.Animal;
import models.Tutor;
import services.RegraNegocioException;
import services.AnimalService;
import services.TutorService;

public class AnimalController {
    private AnimalService service = new AnimalService();
    private TutorService tutorService = new TutorService();
    private Scanner scanner;

    public AnimalController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- Cadastro de Animal ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Documento do tutor (CPF/RG): ");
        String documento = scanner.nextLine();
        System.out.print("Especie (cao, gato, ave, outro): ");
        String especie = scanner.nextLine();

        Animal animal = new Animal(nome, documento, especie);
        preencherDadosComplementares(animal);

        try {
            Animal salvo = service.cadastrar(animal);
            Tutor tutor = tutorService.buscarPorDocumento(salvo.getDocumentoTutor());
            System.out.println("Animal cadastrado na ficha de " + tutor.getNomeCompleto() + ".");
            System.out.println("ID do animal: " + salvo.getId());
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        System.out.println("\n--- Lista de Animais ---");
        List<Animal> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            System.out.println("---");
        }
    }

    public void buscarPorId() {
        System.out.print("\nID do animal: ");
        long id = lerLong();
        Animal animal = service.buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal nao encontrado.");
        } else {
            System.out.println(animal);
        }
    }

    public void buscarPorNome() {
        System.out.print("\nNome do animal: ");
        String nome = scanner.nextLine();
        List<Animal> lista = service.buscarPorNome(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            System.out.println("---");
        }
    }

    public void buscarPorEspecie() {
        System.out.print("\nEspecie do animal: ");
        String especie = scanner.nextLine();
        List<Animal> lista = service.buscarPorEspecie(especie);
        if (lista.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            System.out.println("---");
        }
    }

    public void atualizar() {
        System.out.print("\nID do animal a atualizar: ");
        long id = lerLong();
        Animal existente = service.buscarPorId(id);
        if (existente == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.println("Dados atuais:");
        System.out.println(existente);

        System.out.print("Novo nome: ");
        String nome = manterOuTrocar(existente.getNome());
        System.out.print("Novo documento do tutor: ");
        String documento = manterOuTrocar(existente.getDocumentoTutor());
        System.out.print("Nova especie: ");
        String especie = manterOuTrocar(existente.getEspecie());
        System.out.print("Nova raca: ");
        String raca = manterOuTrocar(existente.getRaca());
        System.out.print("Novo sexo: ");
        String sexo = manterOuTrocar(existente.getSexo());
        System.out.print("Nova pelagem / cor: ");
        String pelagem = manterOuTrocar(existente.getPelagem());
        System.out.print("Nova idade / data de nascimento: ");
        String idade = manterOuTrocar(existente.getIdade());
        System.out.print("Novo historico de vacinas: ");
        String vacinas = manterOuTrocar(existente.getHistoricoVacinas());
        System.out.print("Novas alergias e restricoes: ");
        String alergias = manterOuTrocar(existente.getAlergias());
        System.out.print("Novas condicoes de saude: ");
        String saude = manterOuTrocar(existente.getCondicoesSaude());
        System.out.print("Castrado? (s/n, Enter para manter): ");
        String castradoTexto = scanner.nextLine();
        boolean castrado = existente.isCastrado();
        if (castradoTexto.equalsIgnoreCase("s")) {
            castrado = true;
        } else if (castradoTexto.equalsIgnoreCase("n")) {
            castrado = false;
        }
        System.out.print("Novas observacoes: ");
        String observacoes = manterOuTrocar(existente.getObservacoes());
        System.out.print("Novas anotacoes: ");
        String anotacoes = manterOuTrocar(existente.getAnotacoes());

        Animal novo = new Animal(id, nome, documento, especie, raca, sexo, pelagem, idade,
                vacinas, alergias, saude, castrado, observacoes, anotacoes);

        try {
            if (service.atualizar(id, novo)) {
                System.out.println("Animal atualizado!");
            } else {
                System.out.println("Falha ao atualizar.");
            }
        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletar() {
        System.out.print("\nID do animal a deletar: ");
        long id = lerLong();
        if (service.deletar(id)) {
            System.out.println("Animal removido!");
        } else {
            System.out.println("Animal nao encontrado.");
        }
    }

    private void preencherDadosComplementares(Animal animal) {
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
    }

    private String manterOuTrocar(String atual) {
        String digitado = scanner.nextLine();
        if (digitado.trim().isEmpty()) {
            return atual;
        }
        return digitado;
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
