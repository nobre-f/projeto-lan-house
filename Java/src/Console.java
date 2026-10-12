
public class Console { 
    private String id;

    public Console(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void exibirDadosConsole(){
        System.out.println("id: "+id);
    }


      public boolean possuiId(String id) {
        return this.id.equalsIgnoreCase(id);
    }

	  @Override
	  public String toString() {
		return "Console [id=" + id + "]";
	  }

    

    


}
