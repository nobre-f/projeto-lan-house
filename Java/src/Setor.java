import java.util.ArrayList;

public class Setor {
    private String nomeSetor;
    private Equipe equipe;
    private Plataforma plataforma;
    ArrayList<Console> consoles = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    public Setor(String nomeSetor, Equipe equipe, Plataforma plataforma) {
        this.nomeSetor = nomeSetor;
        this.equipe = equipe;
        this.plataforma = plataforma;
    }
    public String getNomeSetor() {
        return nomeSetor;
    }
    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
    public Equipe getEquipe() {
        return equipe;
    }
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    public Plataforma getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public void addConsole(Console c){
         if (buscarConsolePorId(c.getId()) != null) {
        System.out.println("Já existe um console com este ID no setor.");
    } else {
        consoles.add(c);
        System.out.println("Console cadastrado com sucesso.");
    }
    }

    public void removeConsole(Console c){
        consoles.remove(c);
    }

    public void listarConsoles(){
        for (int i = 0; i < consoles.size(); i++) {
            consoles.get(i).exibirDadosConsole();
        }
    }

    public int quantConsoles(){
        return consoles.size();

    }

    public Console buscarConsolePorId(String id){
        for (int i = 0; i < consoles.size(); i++) {
            if (consoles.get(i).possuiId(id)) {
                return consoles.get(i);
            }
        }
        return null;
    }

    public void adicionarCliente(Cliente c){
        clientes.add(c);
    }

    public void removerCliente(Cliente c){
        clientes.remove(c);
    }

    public void listarClientes(){
        for (int i = 0; i < clientes.size(); i++) {
            clientes.get(i).exibirDadosCliente();
        }
    }

    public int quantClientes(){
        return clientes.size();
    }

      public Cliente buscarClientePorNome(String nome){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNome().equalsIgnoreCase(nome)) {
                return clientes.get(i);
            }
        }

        return null;
    }

    public void exibirDadosSetor(){
        System.out.println("Nome do setor: "+nomeSetor);
        System.out.println("Quantidade de consoles: "+consoles.size());
        System.out.println("Quantidade de clientes: "+clientes.size());
    }
    @Override
    public String toString() {
        return "Setor [nomeSetor=" + nomeSetor + ", equipe=" + equipe + ", plataforma=" + plataforma + ", consoles="
                + consoles + ", clientes=" + clientes + "]";
    }

    public int totalDeHorasReservadas(){
        int total = 0;
        for (int i = 0; i < clientes.size(); i++) {
            total = clientes.get(i).getQuantHorasReservadas();
        }
        return total;
    }

    public double mediaDeHorasReservadasPorCliente(){
        if(clientes.isEmpty()){
            return 0;
        }
       
        return totalDeHorasReservadas()/ clientes.size();
    }

    public int maiorQuantDeHorasReservadasPorCliente(){
        if(clientes.isEmpty()){
            return 0;
        }

        int maior = clientes.get(0).getQuantHorasReservadas();
        for (int i = 1; i < clientes.size(); i++) {
            if(clientes.get(i).getQuantHorasReservadas() > maior)
                maior = clientes.get(i).getQuantHorasReservadas();
        }

        return maior;
    }

      public int menorQuantDeHorasReservadasPorCliente(){
        if(clientes.isEmpty()){
            return 0;
        }

        int menor = clientes.get(0).getQuantHorasReservadas();
        for (int i = 1; i < clientes.size(); i++) {
            if(clientes.get(i).getQuantHorasReservadas() < menor)
                menor = clientes.get(i).getQuantHorasReservadas();
        }

        return menor;
    }

    public double faturamentoTotalSetor(){
       
        return totalDeHorasReservadas() * plataforma.getPrecoPorHora();
    }

    



    

    
    


    



}
