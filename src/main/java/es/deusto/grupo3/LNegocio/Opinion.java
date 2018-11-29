package es.deusto.grupo3.LNegocio;

public class Opinion {

	///OBJETO OPINION: String nombre, String matricula, String puntuacion, String comentario

	String usuario, matricula, puntuacion, comentario;

	public Opinion(String nombre, String matricula, String puntuacion, String comentario){
		this.usuario=nombre;
		this.matricula=matricula;
		this.puntuacion=puntuacion;
		this.comentario=comentario;			
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

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		/*return "Opinion [usuario=" + usuario + ", matricula=" + matricula
				+ ", puntuacion=" + puntuacion + ", comentario="
				+ comentario + "]";*/
		return "Matricula: " + matricula
				+ ", Puntuacion: " + puntuacion + ", Comentario: " + comentario;
	}
}
