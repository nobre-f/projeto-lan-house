

public class Plataforma {
    private String tipoplataforma;
    private String versaoPlataforma;
    private double precoPorHora;
    public Plataforma(String tipoplataforma, String versaoPlataforma, int precoPorHora) {
        this.tipoplataforma = tipoplataforma;
        this.versaoPlataforma = versaoPlataforma;
        this.precoPorHora = precoPorHora;
    }
    public String getTipoplataforma() {
        return tipoplataforma;
    }
    public void setTipoplataforma(String tipoplataforma) {
        this.tipoplataforma = tipoplataforma;
    }
    public String getVersaoPlataforma() {
        return versaoPlataforma;
    }
    public void setVersaoPlataforma(String versaoPlataforma) {
        this.versaoPlataforma = versaoPlataforma;
    }
    public double getPrecoPorHora() {
        return precoPorHora;
    }
    public void setPrecoPorHora(int precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public double calcularPreco(int horas){
          if (horas <= 0) {
        System.out.println("A quantidade de horas deve ser maior que zero.");
        return 0;
    }
    return precoPorHora * horas;
    }

    public void exibirDadosPlataforma(){
        System.out.println("Tipo plataforma: "+tipoplataforma);
        System.out.println("versao plataforma: "+versaoPlataforma);
        System.out.println("Preço: "+precoPorHora);
    }
	@Override
	public String toString() {
		return "Plataforma [tipoplataforma=" + tipoplataforma + ", versaoPlataforma=" + versaoPlataforma
				+ ", precoPorHora=" + precoPorHora + "]";
	}

    

    
    
    
    
    


    
    
}
