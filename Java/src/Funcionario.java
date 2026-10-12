public class Funcionario {
    private String nome;
    private String cpf;
    public Funcionario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;

    }

    public boolean possuiCpf(String cpf) {
        return this.cpf.equals(cpf);
    }

    @Override
    public String toString() {
        return "Funcionario [nome=" + nome + ", cpf=" + cpf + "]";
    }
    public void exibirDadosFuncionario(){
            System.out.println("Nome: "+nome);
            System.out.println("Cpf: "+cpf);
        }

    



}
