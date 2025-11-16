package Eventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Tiquetes.Tiquete_individual;
import Usuarios.Promotor;

public class Evento {
private String tipoEvento;
private int idEvento;
private Date fechaEvento;
private int horaEvento;
private Venue venueAsociado;
private int capacidadEvento;
private int capacidadActualLocalidades;
private TreeMap<Integer, Localidad> localidades;
private Promotor promotorEvento;
private static int idSecuencial = 1;
private int idLocalidad = 1;
private List<Tiquete_individual> tiquetes;
private static String estadoActivo = "ACTIVO";
private static String estadoVendido = "VENDIDO";
private static String estadoCancelado = "CANCELADO";
private static String estadoFinalizado = "Finalizado";
private String estado;
public Evento(int capacidadEvento, String tipoEvento,Date fechaEvento, int horaEvento, Venue venueAsociado, double cobroEmision, int porcentajeServicio, Promotor promotor) 
{
	this.venueAsociado = venueAsociado;
	this.capacidadEvento = capacidadEvento;
	this.tipoEvento = tipoEvento;
	this.fechaEvento = fechaEvento;
	this.horaEvento = horaEvento;
	asignarVenue(venueAsociado);
	this.promotorEvento = promotor;
	idEvento = idSecuencial;
	idSecuencial ++;
	localidades = new TreeMap<Integer, Localidad>();
	tiquetes = new ArrayList<Tiquete_individual>();
	estado = estadoActivo;
	
}

public void setEstado(String estado) {
	if (estado == estadoVendido) {
		this.estado = estadoVendido;
	}
	else if (estado == estadoCancelado) {
		this.estado = estadoCancelado;
	}
	else if (estado == estadoFinalizado) {
		this.estado = estadoFinalizado;
	}
}
public void agregarLocalidad(Localidad localidad) throws Exception{
	if (localidad.getCapacidad() > capacidadEvento || localidad.getCapacidad() + capacidadActualLocalidades > capacidadEvento) {
		throw new Exception("La capacidad de la localidad es mayor de lo que puede soportar el evento");
	}
	localidad.setIdLocalidad(idLocalidad);
	
	localidad.setEventoAsociado(this);
	localidades.put(idLocalidad, localidad);
	capacidadActualLocalidades += localidad.getCapacidad();
	idLocalidad ++;
}
public void eliminarLocalidad(int idLocalidad) {
	if(localidades.get(idLocalidad)!= null) {
		capacidadActualLocalidades -= localidades.get(idLocalidad).getCapacidad();
		localidades.remove(idLocalidad);
	}
}
public void asignarVenue(Venue venue) {
	venueAsociado = venue;
	venueAsociado.setEventoAsociado(this);
}
	

public Localidad getLocalidadPorID(int id) {
	return localidades.get(id);
}
public ArrayList<Localidad> getLocalidadesDisponibles(){
	ArrayList<Localidad> rst = new ArrayList<Localidad>();
	for (Map.Entry<Integer, Localidad> localidad: localidades.entrySet()){
		Localidad valor = localidad.getValue();
		if (valor.getDisponible() == true) {
			rst.add(valor);
		}
	}
	return rst;
}
public int getDisponibilidad() {
	int rst = 0;
	for (Map.Entry<Integer, Localidad> localidad: localidades.entrySet()){
		Localidad valor = localidad.getValue();
		rst += valor.contarDisponibilidad();
	}
	return rst;
}
public String getTipoEvento() {
	return tipoEvento;
}
public void setTipoEvento(String tipoEvento) {
	this.tipoEvento = tipoEvento;
}
public int getIdEvento() {
	return idEvento;
}
public Date getFechaEvento() {
	return fechaEvento;
}
public void setFechaEvento(Date fechaEvento) {
	this.fechaEvento = fechaEvento;
}
public int getHoraEvento() {
	return horaEvento;
}
public void setHoraEvento(int horaEvento) {
	this.horaEvento = horaEvento;
}
public Venue getVenueAsociado() {
	return venueAsociado;
}
public void setVenueAsociado(Venue venueAsociado) {
	this.venueAsociado = venueAsociado;
}
public int getCapacidadEvento() {
	return capacidadEvento;
}
public void setCapacidadEvento(int capacidadEvento) {
	this.capacidadEvento = capacidadEvento;
}
public TreeMap<Integer, Localidad> getLocalidades() {
	return localidades;
}
public void setLocalidades(TreeMap<Integer, Localidad> localidades) {
	this.localidades = localidades;
}


}