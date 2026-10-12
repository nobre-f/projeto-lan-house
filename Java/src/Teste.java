
public class Teste {

    public static void main(String[] args) {

        // Criando funcionários
        Funcionario funcionario1 = new Funcionario("João", "111.111.111-11");
        Funcionario funcionario2 = new Funcionario("Maria", "222.222.222-22");

        // Criando equipe
        Equipe equipe = new Equipe(1);

        equipe.addFuncionario(funcionario1);
        equipe.addFuncionario(funcionario2);

        // Criando plataforma
        Plataforma plataforma = new Plataforma(
                "PlayStation",
                "PS5",
                10
        );

        // Criando consoles
        Console console1 = new Console("C01");
        Console console2 = new Console("C02");

        // Criando setor
        Setor setor = new Setor(
                "Setor PlayStation",
                equipe,
                plataforma
        );

        // Associando consoles ao setor
        setor.addConsole(console1);
        setor.addConsole(console2);

        // Criando clientes
        Cliente cliente1 = new Cliente("Carlos", 5);
        Cliente cliente2 = new Cliente("Ana", 3);

        // Associando clientes ao setor
        setor.adicionarCliente(cliente1);
        setor.adicionarCliente(cliente2);

        // Criando a Lan House
        LanHouse lanHouse = new LanHouse("Game House");

        // Associando o setor à Lan House
        lanHouse.addSetor(setor);


        // ==========================================
        // TESTANDO AS FUNCIONALIDADES
        // ==========================================

        System.out.println("===== LAN HOUSE =====");
        lanHouse.exibirDadosLanHouse();

        System.out.println("\n===== SETORES =====");
        lanHouse.listarSetores();

        System.out.println("\n===== FUNCIONÁRIOS =====");
        equipe.listarFuncionarios();

        System.out.println("\n===== CONSOLES =====");
        setor.listarConsoles();

        System.out.println("\n===== CLIENTES =====");
        setor.listarClientes();

        System.out.println("\n===== PESQUISA DE FUNCIONÁRIO =====");

        Funcionario funcionarioEncontrado =
                equipe.buscarFuncionarioPorCpf("111.111.111-11");

        if (funcionarioEncontrado != null) {
            funcionarioEncontrado.exibirDadosFuncionario();
        } else {
            System.out.println("Funcionário não encontrado.");
        }


        System.out.println("\n===== PESQUISA DE CONSOLE =====");

        Console consoleEncontrado =
                setor.buscarConsolePorId("C01");

        if (consoleEncontrado != null) {
            consoleEncontrado.exibirDadosConsole();
        } else {
            System.out.println("Console não encontrado.");
        }


        System.out.println("\n===== PESQUISA DE CLIENTE =====");

        Cliente clienteEncontrado =
                setor.buscarClientePorNome("Ana");

        if (clienteEncontrado != null) {
            clienteEncontrado.exibirDadosCliente();
        } else {
            System.out.println("Cliente não encontrado.");
        }


        System.out.println("\n===== CÁLCULO DE PREÇO =====");

        double valor = plataforma.calcularPreco(cliente1.getQuantHorasReservadas());

        System.out.println("Valor das horas de " + cliente1.getNome() + ": R$ " + valor);
    }
}
