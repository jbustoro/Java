package controller;

public class Usuario {
	private String cod;
	private String pass;
	private int contVisitas;
	
	public Usuario(){
		
	}
	
	public Usuario(String cod, String pass, int contVisitas){
		this.cod = cod;
		this.pass = pass;
		this.contVisitas = contVisitas;
		
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public void incContVistitas(){
		this.contVisitas++;
		
	}
	
	public int getContVisitas() {
		return contVisitas;
	}


	/*@Override
	public String toString() {
		return cod+","+pass+","+contVisitas;
	}*/
	
	public String toString() {
		return cod;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Usuario){
			Usuario u=(Usuario) obj;
			if(cod.equals(u.getCod()) && pass.equals(u.getPass())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	

}
