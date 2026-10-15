import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Método para limpar o terminal e colocar o cursor no topo
    public static void limparTela() {
        try {
            // Executa o comando cls nativo no Prompt/PowerShell do Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Fallback caso o ambiente limite a execução
            System.out.println("\n".repeat(50));
        }
    }

    // Método para pausar a execução e aguardar o ENTER do usuário
    public static void aguardarEnter(Scanner scanner) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    public static Setor selecionarSetor(Scanner scanner, LanHouse lanHouse) {
        if (lanHouse.quantSetores() == 0) {
            System.out.println("Nenhum setor cadastrado.");
            return null;
        }

        lanHouse.listarSetores();

        System.out.print("Digite o nome do setor: ");
        String nomeSetor = scanner.nextLine();

        Setor setor = lanHouse.buscarSetorPorNome(nomeSetor);

        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return null;
        }

        return setor;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LanHouse lanHouse = new LanHouse("Game House");

        Funcionario funcionario1 = new Funcionario("João", "111.111.111-11");
        Funcionario funcionario2 = new Funcionario("Maria", "222.222.222-22");

        Equipe equipe = new Equipe(1);
        equipe.addFuncionario(funcionario1);
        equipe.addFuncionario(funcionario2);

        Plataforma plataforma = new Plataforma("PlayStation", "PS5", 10);

        Console console1 = new Console("C01");
        Console console2 = new Console("C02");

        Setor setor = new Setor("Setor PlayStation", equipe, plataforma);

        setor.addConsole(console1);
        setor.addConsole(console2);

        Cliente cliente1 = new Cliente("Carlos", 5);
        Cliente cliente2 = new Cliente("Ana", 3);

        setor.adicionarCliente(cliente1);
        setor.adicionarCliente(cliente2);

        lanHouse.addSetor(setor);

        int opcao;

        do {
            limparTela();

            System.out.println("========================================");
            System.out.println("             GAME HOUSE");
            System.out.println("========================================");
            System.out.println("1  - Exibir dados da Lan House");
            System.out.println("2  - Listar setores");
            System.out.println("3  - Cadastrar setor");
            System.out.println("4  - Consultar setor");
            System.out.println("5  - Remover setor");
            System.out.println("6  - Listar funcionários");
            System.out.println("7  - Cadastrar funcionário");
            System.out.println("8  - Consultar funcionário");
            System.out.println("9  - Editar funcionário");
            System.out.println("10 - Remover funcionário");
            System.out.println("11 - Listar consoles");
            System.out.println("12 - Cadastrar console");
            System.out.println("13 - Consultar console");
            System.out.println("14 - Remover console");
            System.out.println("15 - Listar clientes");
            System.out.println("16 - Cadastrar cliente");
            System.out.println("17 - Consultar cliente");
            System.out.println("18 - Editar cliente");
            System.out.println("19 - Remover cliente");
            System.out.println("20 - Adicionar horas");
            System.out.println("21 - Remover horas");
            System.out.println("22 - Indicadores do setor");
            System.out.println("23 - Faturamento do setor");
            System.out.println("24 - Faturamento geral");
            System.out.println("0  - Sair");
            System.out.println("========================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            limparTela();

            switch (opcao) {

                case 1:
                    System.out.println("===== DADOS DA LAN HOUSE =====");
                    lanHouse.exibirDadosLanHouse();
                    aguardarEnter(scanner);
                    break;

                case 2:
                    System.out.println("===== SETORES =====");
                    lanHouse.listarSetores();
                    aguardarEnter(scanner);
                    break;

                case 3:
                    System.out.println("===== CADASTRAR SETOR =====");
                    System.out.print("Nome do setor: ");
                    String nomeSetor = scanner.nextLine();

                    if (lanHouse.buscarSetorPorNome(nomeSetor) != null) {
                        System.out.println("Já existe um setor com este nome.");
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Código da equipe: ");
                    int codigoEquipe = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Tipo da plataforma (Ex: PC, PlayStation, Xbox): ");
                    String tipoPlataforma = scanner.nextLine();

                    Plataforma plataformaDefinitiva = null;

                    // VERIFICAÇÃO INTELIGENTE: Se for PC ou Notebook, libera o Catálogo Online!
                    if (tipoPlataforma.equalsIgnoreCase("PC") || tipoPlataforma.equalsIgnoreCase("notebook")) {
                        System.out.println("\n[SISTEMA] Tipo '" + tipoPlataforma + "' detectado. Liberando Catálogo Online do Google Sheets...");
                        System.out.println("1 - Escolher modelo do Catálogo Online");
                        System.out.println("2 - Digitar o modelo manualmente");
                        System.out.print("Escolha uma opção: ");
                        int modoCarga = scanner.nextInt();
                        scanner.nextLine();

                        if (modoCarga == 1) {
                            System.out.println("\nBuscando modelos disponíveis na nuvem...");
                            ArrayList<Plataforma> opcoesCatalogo = ControleDeCarga.obterCatalogoPlanilha();

                            if (opcoesCatalogo.isEmpty()) {
                                System.out.println("Não foi possível conectar à API. Redirecionando para o modo manual...");
                            } else {
                                System.out.println("\n--- Modelos de Notebooks Encontrados na Planilha ---");
                                for (int k = 0; k < opcoesCatalogo.size(); k++) {
                                    Plataforma pItem = opcoesCatalogo.get(k);
                                    System.out.printf("%d - %s | Modelo: %s (Preço sugerido: R$ %.2f/h)%n", 
                                        (k + 1), 
                                        pItem.getTipoplataforma(), 
                                        pItem.getVersaoPlataforma(),
                                        pItem.getPrecoPorHora());
                                }
                                System.out.print("\nSelecione o número do modelo para importar: ");
                                int escolhaModelo = scanner.nextInt();
                                scanner.nextLine();

                                if (escolhaModelo > 0 && escolhaModelo <= opcoesCatalogo.size()) {
                                    plataformaDefinitiva = opcoesCatalogo.get(escolhaModelo - 1);
                                    // Sobrescreve o tipo genérico "PC" pelo nome detalhado da marca vindo da API
                                    tipoPlataforma = plataformaDefinitiva.getTipoplataforma(); 
                                    System.out.println("\nPlataforma importada com sucesso!");
                                } else {
                                    System.out.println("Opção inválida. Prosseguindo com cadastro manual...");
                                }
                            }
                        }
                    }

                    // Se NÃO for PC/Notebook (ou se o usuário escolheu preencher manualmente)
                    if (plataformaDefinitiva == null) {
                        System.out.print("Versão/Modelo da plataforma (Ex: PS5, Series X): ");
                        String versaoPlataforma = scanner.nextLine();

                        System.out.print("Preço por hora: R$ ");
                        int precoHora = scanner.nextInt();
                        scanner.nextLine();

                        if (precoHora <= 0) {
                            System.out.println("O preço por hora deve ser maior que zero.");
                            aguardarEnter(scanner);
                            break;
                        }

                        plataformaDefinitiva = new Plataforma(tipoPlataforma, versaoPlataforma, precoHora);
                    }

                    // Cria e adiciona o setor utilizando a sua estrutura original de POO
                    Equipe novaEquipe = new Equipe(codigoEquipe);
                    Setor novoSetor = new Setor(nomeSetor, novaEquipe, plataformaDefinitiva);
                    lanHouse.addSetor(novoSetor);

                    System.out.println("Setor cadastrado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 4:
                    System.out.println("===== CONSULTAR SETOR =====");
                    System.out.print("Nome do setor: ");
                    String consultaSetor = scanner.nextLine();

                    Setor setorEncontrado = lanHouse.buscarSetorPorNome(consultaSetor);

                    if (setorEncontrado != null) {
                        setorEncontrado.exibirDadosSetor();
                    } else {
                        System.out.println("Setor não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 5:
                    System.out.println("===== REMOVER SETOR =====");
                    System.out.print("Nome do setor: ");
                    String nomeRemoverSetor = scanner.nextLine();

                    Setor setorRemover = lanHouse.buscarSetorPorNome(nomeRemoverSetor);

                    if (setorRemover != null) {
                        lanHouse.removerSetores(setorRemover);
                        System.out.println("Setor removido com sucesso.");
                    } else {
                        System.out.println("Setor não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 6:
                    System.out.println("===== LISTAR FUNCIONÁRIOS =====");
                    Setor setorFuncionario = selecionarSetor(scanner, lanHouse);

                    if (setorFuncionario != null) {
                        setorFuncionario.getEquipe().listarFuncionarios();
                    }
                    aguardarEnter(scanner);
                    break;

                case 7:
                    System.out.println("===== CADASTRAR FUNCIONÁRIO =====");
                    Setor setorNovoFuncionario = selecionarSetor(scanner, lanHouse);

                    if (setorNovoFuncionario == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome: ");
                    String nomeFuncionario = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpfFuncionario = scanner.nextLine();

                    Funcionario novoFuncionario = new Funcionario(
                            nomeFuncionario,
                            cpfFuncionario
                    );

                    setorNovoFuncionario.getEquipe().addFuncionario(novoFuncionario);
                    System.out.println("Funcionário cadastrado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 8:
                    System.out.println("===== CONSULTAR FUNCIONÁRIO =====");
                    Setor setorConsultaFuncionario = selecionarSetor(scanner, lanHouse);

                    if (setorConsultaFuncionario == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("CPF: ");
                    String cpfConsulta = scanner.nextLine();

                    Funcionario funcionarioEncontrado =
                            setorConsultaFuncionario.getEquipe()
                                    .buscarFuncionarioPorCpf(cpfConsulta);

                    if (funcionarioEncontrado != null) {
                        funcionarioEncontrado.exibirDadosFuncionario();
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 9:
                    System.out.println("===== EDITAR FUNCIONÁRIO =====");
                    Setor setorEditarFuncionario = selecionarSetor(scanner, lanHouse);

                    if (setorEditarFuncionario == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("CPF do funcionário: ");
                    String cpfEditar = scanner.nextLine();

                    Funcionario funcionarioEditar =
                            setorEditarFuncionario.getEquipe()
                                    .buscarFuncionarioPorCpf(cpfEditar);

                    if (funcionarioEditar == null) {
                        System.out.println("Funcionário não encontrado.");
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Novo nome: ");
                    String novoNomeFuncionario = scanner.nextLine();

                    funcionarioEditar.setNome(novoNomeFuncionario);

                    System.out.println("Funcionário editado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 10:
                    System.out.println("===== REMOVER FUNCIONÁRIO =====");
                    Setor setorRemoverFuncionario = selecionarSetor(scanner, lanHouse);

                    if (setorRemoverFuncionario == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("CPF do funcionário: ");
                    String cpfRemover = scanner.nextLine();

                    Funcionario funcionarioRemover =
                            setorRemoverFuncionario.getEquipe()
                                    .buscarFuncionarioPorCpf(cpfRemover);

                    if (funcionarioRemover != null) {
                        setorRemoverFuncionario.getEquipe()
                                .removerFuncionario(funcionarioRemover);

                        System.out.println("Funcionário removido com sucesso.");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 11:
                    System.out.println("===== LISTAR CONSOLES =====");
                    Setor setorListarConsole = selecionarSetor(scanner, lanHouse);

                    if (setorListarConsole != null) {
                        setorListarConsole.listarConsoles();
                    }
                    aguardarEnter(scanner);
                    break;

                case 12:
                    System.out.println("===== CADASTRAR CONSOLE =====");
                    Setor setorNovoConsole = selecionarSetor(scanner, lanHouse);

                    if (setorNovoConsole == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("ID do console: ");
                    String idNovoConsole = scanner.nextLine();

                    Console novoConsole = new Console(idNovoConsole);

                    setorNovoConsole.addConsole(novoConsole);
                    System.out.println("Console cadastrado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 13:
                    System.out.println("===== CONSULTAR CONSOLE =====");
                    Setor setorConsultaConsole = selecionarSetor(scanner, lanHouse);

                    if (setorConsultaConsole == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("ID do console: ");
                    String idConsulta = scanner.nextLine();

                    Console consoleEncontrado =
                            setorConsultaConsole.buscarConsolePorId(idConsulta);

                    if (consoleEncontrado != null) {
                        consoleEncontrado.exibirDadosConsole();
                    } else {
                        System.out.println("Console não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 14:
                    System.out.println("===== REMOVER CONSOLE =====");
                    Setor setorRemoverConsole = selecionarSetor(scanner, lanHouse);

                    if (setorRemoverConsole == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("ID do console: ");
                    String idRemover = scanner.nextLine();

                    Console consoleRemover =
                            setorRemoverConsole.buscarConsolePorId(idRemover);

                    if (consoleRemover != null) {
                        setorRemoverConsole.removeConsole(consoleRemover);
                        System.out.println("Console removido com sucesso.");
                    } else {
                        System.out.println("Console não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 15:
                    System.out.println("===== LISTAR CLIENTES =====");
                    Setor setorListarCliente = selecionarSetor(scanner, lanHouse);

                    if (setorListarCliente != null) {
                        setorListarCliente.listarClientes();
                    }
                    aguardarEnter(scanner);
                    break;

                case 16:
                    System.out.println("===== CADASTRAR CLIENTE =====");
                    Setor setorNovoCliente = selecionarSetor(scanner, lanHouse);

                    if (setorNovoCliente == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String novoNomeCliente = scanner.nextLine();

                    System.out.print("Quantidade inicial de horas: ");
                    int horasCliente = scanner.nextInt();
                    scanner.nextLine();

                    if (horasCliente < 0) {
                        System.out.println("A quantidade de horas não pode ser negativa.");
                        aguardarEnter(scanner);
                        break;
                    }

                    Cliente novoCliente = new Cliente(
                            novoNomeCliente,
                            horasCliente
                    );

                    setorNovoCliente.adicionarCliente(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 17:
                    System.out.println("===== CONSULTAR CLIENTE =====");
                    Setor setorConsultaCliente = selecionarSetor(scanner, lanHouse);

                    if (setorConsultaCliente == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeConsultaCliente = scanner.nextLine();

                    Cliente clienteEncontrado =
                            setorConsultaCliente.buscarClientePorNome(nomeConsultaCliente);

                    if (clienteEncontrado != null) {
                        clienteEncontrado.exibirDadosCliente();
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 18:
                    System.out.println("===== EDITAR CLIENTE =====");
                    Setor setorEditarCliente = selecionarSetor(scanner, lanHouse);

                    if (setorEditarCliente == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeEditarCliente = scanner.nextLine();

                    Cliente clienteEditar =
                            setorEditarCliente.buscarClientePorNome(nomeEditarCliente);

                    if (clienteEditar == null) {
                        System.out.println("Cliente não encontrado.");
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    clienteEditar.setNome(novoNome);

                    System.out.println("Cliente editado com sucesso.");
                    aguardarEnter(scanner);
                    break;

                case 19:
                    System.out.println("===== REMOVER CLIENTE =====");
                    Setor setorRemoverCliente = selecionarSetor(scanner, lanHouse);

                    if (setorRemoverCliente == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeRemoverCliente = scanner.nextLine();

                    Cliente clienteRemover =
                            setorRemoverCliente.buscarClientePorNome(nomeRemoverCliente);

                    if (clienteRemover != null) {
                        setorRemoverCliente.removerCliente(clienteRemover);
                        System.out.println("Cliente removido com sucesso.");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    aguardarEnter(scanner);
                    break;

                case 20:
                    System.out.println("===== ADICIONAR HORAS =====");
                    Setor setorAdicionarHoras = selecionarSetor(scanner, lanHouse);

                    if (setorAdicionarHoras == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeAdicionarHoras = scanner.nextLine();

                    Cliente clienteAdicionarHoras =
                            setorAdicionarHoras.buscarClientePorNome(nomeAdicionarHoras);

                    if (clienteAdicionarHoras == null) {
                        System.out.println("Cliente não encontrado.");
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Quantidade de horas: ");
                    int horasAdicionar = scanner.nextInt();
                    scanner.nextLine();

                    clienteAdicionarHoras.addHorasReservadas(horasAdicionar);

                    System.out.println(
                            "Total de horas: "
                                    + clienteAdicionarHoras.getQuantHorasReservadas()
                    );
                    aguardarEnter(scanner);
                    break;

                case 21:
                    System.out.println("===== REMOVER HORAS =====");
                    Setor setorRemoverHoras = selecionarSetor(scanner, lanHouse);

                    if (setorRemoverHoras == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeRemoverHoras = scanner.nextLine();

                    Cliente clienteRemoverHoras =
                            setorRemoverHoras.buscarClientePorNome(nomeRemoverHoras);

                    if (clienteRemoverHoras == null) {
                        System.out.println("Cliente não encontrado.");
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.print("Quantidade de horas: ");
                    int horasRemover = scanner.nextInt();
                    scanner.nextLine();

                    clienteRemoverHoras.removerHorasReservadas(horasRemover);

                    System.out.println(
                            "Total de horas: "
                                    + clienteRemoverHoras.getQuantHorasReservadas()
                    );
                    aguardarEnter(scanner);
                    break;

                case 22:
                    System.out.println("===== INDICADORES DO SETOR =====");
                    Setor setorIndicadores = selecionarSetor(scanner, lanHouse);

                    if (setorIndicadores == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.println(
                            "Setor: " + setorIndicadores.getNomeSetor()
                    );

                    System.out.println(
                            "Total de horas reservadas: "
                                    + setorIndicadores.totalDeHorasReservadas()
                    );

                    System.out.printf(
                            "Média de horas por cliente: %.2f%n",
                            setorIndicadores.mediaDeHorasReservadasPorCliente()
                    );

                    System.out.println(
                            "Maior quantidade de horas: "
                                    + setorIndicadores.maiorQuantDeHorasReservadasPorCliente()
                    );

                    System.out.println(
                            "Menor quantidade de horas: "
                                    + setorIndicadores.menorQuantDeHorasReservadasPorCliente()
                    );

                    System.out.printf(
                            "Faturamento total: R$ %.2f%n",
                            setorIndicadores.faturamentoTotalSetor()
                    );
                    aguardarEnter(scanner);
                    break;

                case 23:
                    System.out.println("===== FATURAMENTO DO SETOR =====");
                    Setor setorFaturamento = selecionarSetor(scanner, lanHouse);

                    if (setorFaturamento == null) {
                        aguardarEnter(scanner);
                        break;
                    }

                    System.out.println(
                            "Setor: " + setorFaturamento.getNomeSetor()
                    );

                    System.out.println(
                            "Total de horas: "
                                    + setorFaturamento.totalDeHorasReservadas()
                    );

                    System.out.printf(
                            "Preço por hora: R$ %.2f%n",
                            setorFaturamento.getPlataforma().getPrecoPorHora()
                    );

                    System.out.printf(
                            "Faturamento: R$ %.2f%n",
                            setorFaturamento.faturamentoTotalSetor()
                    );
                    aguardarEnter(scanner);
                    break;

                case 24:
                    System.out.println("===== FATURAMENTO GERAL =====");

                    System.out.println(
                            "Lan House: " + lanHouse.getNome()
                    );

                    System.out.printf(
                            "Faturamento total: R$ %.2f%n",
                            lanHouse.FaturamentoGeral()
                    );
                    aguardarEnter(scanner);
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    aguardarEnter(scanner);
            }

        } while (opcao != 0);

        scanner.close();
    }

}