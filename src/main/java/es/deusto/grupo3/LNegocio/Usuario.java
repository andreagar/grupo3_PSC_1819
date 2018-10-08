package es.deusto.grupo3.LNegocio;

public class Usuario {

	String password;

	public Usuario(String nombre, String password, int punt, String fecha){
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
