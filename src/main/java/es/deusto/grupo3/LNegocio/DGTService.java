package es.deusto.grupo3.LNegocio;

public class DGTService {
	IDGT dgt;
	
	public int aviso (int x, int y){
		return dgt.alerta(x, y);
	}
	
	public boolean estadoGeneral(){
		return dgt.estado();
	}
	
	public String estadoCarreterasCiudad(String ciudad){
		return dgt.estadoCarreteras(ciudad);
	}

}


