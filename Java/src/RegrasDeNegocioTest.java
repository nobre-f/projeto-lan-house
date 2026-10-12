import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// JUnit
public class RegrasDeNegocioTest {

    @Test
    void naoDevePermitirHorasNegativasNoCliente() {
        Cliente cliente = new Cliente("Carlos", -5);
        assertEquals(0, cliente.getQuantHorasReservadas());
    }

    @Test
    void deveAdicionarHorasAoClienteComSucesso() {
        Cliente cliente = new Cliente("Ana", 3);
        cliente.addHorasReservadas(2);
        assertEquals(5, cliente.getQuantHorasReservadas());
    }

    @Test
    void deveCalcularPrecoDaPlataformaCorretamente() {
        Plataforma plataforma = new Plataforma("PlayStation", "PS5", 10);
        double valorTotal = plataforma.calcularPreco(5);
        assertEquals(50.0, valorTotal, 0.001);
    }

    @Test
    void naoDevePermitirCadastrarFuncionariosComMesmoCpf() {
        Equipe equipe = new Equipe(1);
        Funcionario f1 = new Funcionario("João", "111.111.111-11");
        Funcionario f2 = new Funcionario("Maria", "111.111.111-11");

        equipe.addFuncionario(f1);
        equipe.addFuncionario(f2);

        assertEquals(1, equipe.quantFuncionarios());
    }
}