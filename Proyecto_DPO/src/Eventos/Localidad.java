package Eventos;

import java.util.ArrayList;
import java.util.List;

public class Localidad {
	private int idLocalidad = 0;
	private double precio;
	private String caracteristicas;
	private boolean esNumerada;
	private boolean disponible;
	private int vendidos = 0;
	private List<Asiento> asientosDisponibles;
	private List<Asiento> asientosOcupados;
	private Evento eventoAsociado;
	private int numAsiento = 1;
	private char fila = 'A';
	private int capacidad;
	private Oferta oferta = null;
	
	public Localidad(Evento evento, double precio, String caracteristicas, boolean esNumerada, int capacidad)throws Exception 
	{
		this.precio = precio;
		this.caracteristicas = caracteristicas;
		this.esNumerada = esNumerada;
		if (esNumerada == true) {
			this.asientosDisponibles = new ArrayList<Asiento>();
			this.asientosOcupados = new ArrayList<Asiento>();
		}
		disponible = true;
		this.eventoAsociado = evento;
		this.capacidad = capacidad;
	}
	public List<Asiento>getAsientosDisponibles() {
		return asientosDisponibles;
	}
	public List<Asiento>getAsientosOcupados() {
		return asientosOcupados;
	}
	public void asociarOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public void reservar(Asiento asiento) {
		if(esNumerada == true) {
			asientosDisponibles.remove(asiento);
			asientosOcupados.add(asiento);
			vendidos ++;
		}
		else {
		vendidos ++;
		if (vendidos == capacidad) {
			disponible = false;
		}
		}
	}
	public void liberar(Asiento asiento) {
		if(esNumerada == true) {
			asientosOcupados.remove(asiento);
			asientosDisponibles.add(asiento);
			vendidos --;
		}
		else {
		vendidos --;
		}
	}
	public int contarDisponibilidad() {
		if (capacidad == vendidos) {
			return 0;
		}
		else {
			return capacidad - vendidos;
		}
	}
	public boolean tieneOferta() {
		if (oferta != null) {
			return true;
		}
		else {
			return false;
		}
	}
	public void agregarAsientos(int asientoXFila) throws Exception{
		//segun el asiento x fila generar todos los asientos hasta llegar a una capacidad maxima y añadirlos todos en asientos disponibles
		
		for(int i = 0; i < capacidad; i++) {
			Asiento nuevo = new Asiento(fila, numAsiento,this);
			asientosDisponibles.add(nuevo);
			if (numAsiento == asientoXFila) {
				numAsiento = 1;
				fila ++;
			}
			else {
				numAsiento ++;
			}
			
		}
	}
	public boolean getDisponible() {
		return disponible;
	}
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Evento getEventoAsociado() {
		return eventoAsociado;
	}
	public void setEventoAsociado(Evento eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public void setIdLocalidad(int idLocalidad){
		this.idLocalidad = idLocalidad;
	}


}
