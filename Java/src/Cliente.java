public class Cliente {
    private String nome;
    private int quantHorasReservadas;
    public Cliente(String nome, int quantHorasReservadas) {
        this.nome = nome;
        
    
        if (quantHorasReservadas >= 0) {
        this.quantHorasReservadas = quantHorasReservadas;
   
    } else {
        this.quantHorasReservadas = 0;
        System.out.println("A quantidade de horas não pode ser negativa.");
    }
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantHorasReservadas() {
        return quantHorasReservadas;
    }
    public void setQuantHorasReservadas(int quantHorasReservadas) {
        if (quantHorasReservadas >= 0) {
        this.quantHorasReservadas = quantHorasReservadas;
    } else {
        System.out.println("A quantidade de horas não pode ser negativa.");
    }
    }

    public void  addHorasReservadas(int horas) {
        quantHorasReservadas += horas;
    }

    public void removerHorasReservadas(int horas){
        if (horas <= 0) {
        System.out.println("A quantidade de horas deve ser maior que zero.");
    } else if (horas <= quantHorasReservadas) {
        quantHorasReservadas -= horas;
    } else {
        System.out.println("Não é possível remover mais horas do que o cliente possui.");
    }
    }

    public void exibirDadosCliente(){
        System.out.println("Nome: "+nome);
        System.out.println("Quantidade de horas reservadas: "+quantHorasReservadas);
    }

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", quantHorasReservadas=" + quantHorasReservadas + "]";
	}

    public boolean possuiHoras(int horas){
        return quantHorasReservadas >= horas;
    }

}
