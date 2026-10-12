import java.util.ArrayList;

public class Equipe {
    private int codigoEquipe;
    ArrayList<Funcionario> funcionarios = new ArrayList<>();
    public Equipe(int codigoEquipe) {
        this.codigoEquipe = codigoEquipe;
    }
    public int getCodigoEquipe() {
        return codigoEquipe;
    }
    public void setCodigoEquipe(int codigoEquipe) {
        this.codigoEquipe = codigoEquipe;
    }

    public void addFuncionario(Funcionario f){
   
    if (buscarFuncionarioPorCpf(f.getCpf()) != null) {
        System.out.println("Funcionário com este CPF já está cadastrado na equipe.");
    } else {
        funcionarios.add(f);
        System.out.println("Funcionário cadastrado com sucesso.");
    }
    }


    public void removerFuncionario(Funcionario f){
        funcionarios.remove(f);
    }

    public void listarFuncionarios(){
        for (int i = 0; i < funcionarios.size(); i++) {
        funcionarios.get(i).exibirDadosFuncionario();
        }
    }

    public int quantFuncionarios(){
        return funcionarios.size();
    }

    public Funcionario buscarFuncionarioPorCpf(String cpf){
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).possuiCpf(cpf)) {
                return funcionarios.get(i);
            }
        }

        return null;
    }
    
	@Override
	public String toString() {
		return "Equipe [codigoEquipe=" + codigoEquipe + ", funcionarios=" + funcionarios + "]";
	}

    


    

}
