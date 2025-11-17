package Eventos;

public class Asiento {
	private int numeroAsiento;
	private boolean disponible;
	private char fila;
	public Localidad localidadAsociada;
	
	public Asiento(char fila, int numero, Localidad localidad) {
		this.fila = fila;
		this.numeroAsiento = numero;
		localidadAsociada = localidad;
		
	}
	public int getNumeroAsiento() {
		return numeroAsiento;
	}

	public boolean isDisponible() {
		return disponible;
	}
	public char getFila() {
		return fila;
	}
	public void setLocalidadAsociada(Localidad localidad) {
		this.localidadAsociada = localidad;
	}
	public void reservar() {
		disponible = false;
		localidadAsociada.reservar(this);
	}
	public void liberar() {
		disponible = true;
		localidadAsociada.liberar(this);
	}
}
