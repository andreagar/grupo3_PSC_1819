package es.deusto.grupo3.LNegocio;

public class Coche {
	
	///OBJETO COCHE: matricula, marca, modelo, precio, alquilado, comprado, averiado, imagen

	String marca, modelo, matricula, imagen;
	double precio;
	boolean alquilado, comprado, averiado;

	public Coche(String marca, String modelo, String matricula, double precio, boolean alquilado, boolean comprado, boolean averiado, String imagen){

		this.marca=marca;
		this.modelo=modelo;
		this.matricula=matricula;
		this.precio=precio;
		this.alquilado=alquilado;
		this.comprado=comprado;
		this.averiado=averiado;
		this.imagen = imagen;
		
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String toString(){
		return matricula + ", " + modelo + ", " + marca + ", " + alquilado + ", " + comprado + ", " + averiado + ", " + imagen;
	}
}
