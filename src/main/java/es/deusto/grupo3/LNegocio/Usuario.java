package es.deusto.grupo3.LNegocio;

public class Usuario {

	///OBJETO USUARIO: nombre, password
	
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
