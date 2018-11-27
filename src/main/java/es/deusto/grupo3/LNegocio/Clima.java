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
		return this.ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getTemperatura() {
		return this.temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPrecipitacion() {
		return this.precipitacion;
	}
	public void setPrecipitacion(int precipitacion) {
		this.precipitacion = precipitacion;
	}
}
