package Eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import tiquetes.TiqueteIndividual;

public class Localidad {
	private int idLocalidad = 0;
	private double precio;
	private String caracteristicas;
	private boolean esNumerada;
	private boolean disponible;
	private int vendidos = 0;
	private Map<String, TiqueteIndividual> disponibles;
	private Map<String, TiqueteIndividual> ocupados;
	private Evento eventoAsociado;
	private int numAsiento = 1;
	private char fila = 'A';
	private int capacidad;
	private Oferta oferta = null;
	
	
	public Localidad(Evento evento, double precio, String caracteristicas, boolean esNumerada, int capacidad, int asientoXFila) 
	{
		this.precio = precio;
		this.caracteristicas = caracteristicas;
		disponibles = new TreeMap<String, TiqueteIndividual>();
		ocupados = new TreeMap<String, TiqueteIndividual>();
		if (esNumerada == true) {
			agregarTiquetes(asientoXFila);
		}
		else {
			agregarTiquetes();
		}
		this.esNumerada = esNumerada;
		disponible = true;
		this.eventoAsociado = evento;
		this.capacidad = capacidad;
		
	}
	public void asociarOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public void reservar(TiqueteIndividual tiquete) {
		String id = tiquete.getId();
		if(esNumerada == true) {
			disponibles.remove(id);
			ocupados.put(id, tiquete);
			vendidos ++;
		}
		else {
		vendidos ++;
		if (vendidos == capacidad) {
			disponible = false;
		}
		}
	}
	public void liberar(TiqueteIndividual tiquete) {
		String id = tiquete.getId();
		if(esNumerada == true) {
			ocupados.remove(id);
			disponibles.put(id, tiquete);
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
	public void agregarTiquetes(){
		
		for(int i = 0; i < capacidad; i++) {
			TiqueteIndividual nuevo = new TiqueteIndividual(eventoAsociado, this, eventoAsociado.getPorcentajeServicio(), eventoAsociado.getCobroEmision());
			disponibles.put(nuevo.getId(), nuevo);
		}
	}
	public void agregarTiquetes(int asientoXFila) {//segun el asiento x fila generar todos los asientos hasta llegar a una capacidad maxima y añadirlos todos en asientos disponibles
		for(int i = 0; i < capacidad; i++) {
			TiqueteIndividual nuevo = new TiqueteIndividual(eventoAsociado, this, eventoAsociado.getPorcentajeServicio(), eventoAsociado.getCobroEmision());
			disponibles.put(nuevo.getId(), nuevo);
			numAsiento ++;
			if(i == asientoXFila) {
				fila ++;
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
