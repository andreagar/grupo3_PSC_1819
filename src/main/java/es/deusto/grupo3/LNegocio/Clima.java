package es.deusto.grupo3.LNegocio;

public class Clima {

	String ciudad;
	double temperatura;
	String estado;
	int precipitacion;
	
	public Clima(String ciudad, double temperatura, String estado, int precipitacion){
		this.ciudad = ciudad;
		this.temperatura = temperatura;
		this.estado = estado;
		this.precipitacion = precipitacion;
	}
	
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPrecipitacion() {
		return precipitacion;
	}
	public void setPrecipitacion(int precipitacion) {
		this.precipitacion = precipitacion;
	}
}