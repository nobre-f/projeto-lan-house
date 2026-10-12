import java.util.ArrayList;

public class LanHouse {
    private String nome;
    ArrayList<Setor> setores = new ArrayList<>();
    public LanHouse(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addSetor(Setor s){
        setores.add(s);
    }
    public void removerSetores(Setor s){
        setores.remove(s);
    }

    public void listarSetores(){
        for (int i = 0; i < setores.size(); i++) {
            setores.get(i).exibirDadosSetor();
        }
    }

    public int quantSetores(){
        return setores.size();
    }

    public Setor buscarSetorPorNome(String nome) {
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getNomeSetor().equalsIgnoreCase(nome)) {
                return setores.get(i);
            }
        }

        return null;
    }

    public void exibirDadosLanHouse() {
        System.out.println("Nome da Lan House: " + nome);
        System.out.println("Quantidade de setores: " + setores.size());
    }
    @Override
    public String toString() {
        return "LanHouse [nome=" + nome + ", setores=" + setores + "]";
    }

    public double FaturamentoGeral(){
        double total = 0; 
        for (int i = 0; i < setores.size(); i++) {
            total += setores.get(i).faturamentoTotalSetor();
        }

        return total;
    }

    
    

    

}
