package es.deusto.grupo3.LNegocio;

public class Asignaciones {

	String usuario, matricula;
	boolean alquilado, comprado, averiado;

	public Asignaciones(String usuario, String matricula, boolean alquilado, boolean comprado, boolean averiado){

		this.matricula=matricula;
		this.usuario=usuario;
		this.alquilado=alquilado;
		this.comprado=comprado;
		this.averiado=averiado;
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public boolean getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	public boolean getComprado() {
		return comprado;
	}

	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	
	public boolean getAveriado() {
		return averiado;
	}

	public void setAveriado(boolean averiado) {
		this.averiado = averiado;
	}
	
	public String toString(){
		if(this.getAlquilado()==true){
			return "Usuario: "+ usuario + ", Matricula: " + matricula + ", Alquilado";
		}
		if(this.getComprado()==true){
			return "Usuario: "+ usuario + ", Matricula: " + matricula + ", Comprado";
		}
		if(this.getAveriado()==true){
			return "Usuario: "+ usuario + ", Matricula: " + matricula + ", Averiado";
		}
		else{
			return "Error";
		}
		
		
	}
}
