package es.deusto.grupo3.LNegocio;

public class Usuario {

	String nombre;
	String password;

	public Usuario(String nombre, String password){
		this.password = password;
	}
	
	public String getNombre(){
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
