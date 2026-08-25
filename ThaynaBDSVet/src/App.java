import java.util.Scanner;
import controllers.AnimalController;
import controllers.ProdutoController;
import controllers.SetorController;
import controllers.TutorController;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TutorController tutorController = new TutorController(scanner);
        AnimalController animalController = new AnimalController(scanner);
        SetorController setorController = new SetorController(scanner);
        ProdutoController produtoController = new ProdutoController(scanner);

        setorController.carregarSetoresIniciais();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite um numero.");
                continue;
            }

            switch (opcao) {
                case 1:
                    tutorController.cadastrar();
                    break;
                case 2:
                    tutorController.cadastrarAnimalNaFicha();
                    break;
                case 3:
                    tutorController.vincularAnimalExistente();
                    break;
                case 4:
                    tutorController.listar();
                    break;
                case 5:
                    tutorController.buscarPorId();
                    break;
                case 6:
                    tutorController.buscarPorNome();
                    break;
                case 7:
                    tutorController.atualizar();
                    break;
                case 8:
                    tutorController.adicionarContato();
                    break;
                case 9:
                    tutorController.deletar();
                    break;
                case 10:
                    animalController.cadastrar();
                    break;
                case 11:
                    animalController.listar();
                    break;
                case 12:
                    animalController.buscarPorId();
                    break;
                case 13:
                    animalController.buscarPorNome();
                    break;
                case 14:
                    animalController.buscarPorEspecie();
                    break;
                case 15:
                    animalController.atualizar();
                    break;
                case 16:
                    animalController.deletar();
                    break;
                case 17:
                    setorController.cadastrar();
                    break;
                case 18:
                    setorController.listar();
                    break;
                case 19:
                    setorController.buscarPorId();
                    break;
                case 20:
                    setorController.buscarPorNome();
                    break;
                case 21:
                    setorController.atualizar();
                    break;
                case 22:
                    setorController.deletar();
                    break;
                case 23:
                    produtoController.cadastrar();
                    break;
                case 24:
                    produtoController.listar();
                    break;
                case 25:
                    produtoController.buscarPorId();
                    break;
                case 26:
                    produtoController.buscarPorNome();
                    break;
                case 27:
                    produtoController.buscarPorSetor();
                    break;
                case 28:
                    produtoController.buscarPorFaixaDeValor();
                    break;
                case 29:
                    produtoController.atualizar();
                    break;
                case 30:
                    produtoController.deletar();
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema ThaynaBDSVet. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("     ThaynaBDSVet - Sistema de Cadastro     ");
        System.out.println("============================================");
        System.out.println("--- TUTORES ---");
        System.out.println("1  - Cadastrar tutor");
        System.out.println("2  - Cadastrar animal na ficha do tutor");
        System.out.println("3  - Vincular animal existente a outro tutor");
        System.out.println("4  - Listar tutores");
        System.out.println("5  - Buscar tutor por ID");
        System.out.println("6  - Buscar tutor por nome");
        System.out.println("7  - Atualizar tutor");
        System.out.println("8  - Adicionar contato ao tutor");
        System.out.println("9  - Deletar tutor");
        System.out.println("--- ANIMAIS ---");
        System.out.println("10 - Cadastrar animal");
        System.out.println("11 - Listar animais");
        System.out.println("12 - Buscar animal por ID");
        System.out.println("13 - Buscar animal por nome");
        System.out.println("14 - Buscar animal por especie");
        System.out.println("15 - Atualizar animal");
        System.out.println("16 - Deletar animal");
        System.out.println("--- SETORES ---");
        System.out.println("17 - Cadastrar setor");
        System.out.println("18 - Listar setores");
        System.out.println("19 - Buscar setor por ID");
        System.out.println("20 - Buscar setor por nome");
        System.out.println("21 - Atualizar setor");
        System.out.println("22 - Deletar setor");
        System.out.println("--- PRODUTOS ---");
        System.out.println("23 - Cadastrar produto");
        System.out.println("24 - Listar produtos");
        System.out.println("25 - Buscar produto por ID");
        System.out.println("26 - Buscar produto por nome");
        System.out.println("27 - Buscar produto por setor");
        System.out.println("28 - Buscar produto por faixa de valor");
        System.out.println("29 - Atualizar produto");
        System.out.println("30 - Deletar produto");
        System.out.println("--- SISTEMA ---");
        System.out.println("0  - Sair");
        System.out.println("============================================");
    }
}
