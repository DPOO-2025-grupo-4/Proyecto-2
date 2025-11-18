package Usuarios;

import java.time.LocalDateTime;
import java.util.Objects;

import Eventos.Evento;
import Eventos.Finanzas;
import Eventos.Venue;
import uniandes.dpoo.tiquetes.TiqueteIndividual;

public class Administrador extends Usuario {
	public Administrador(String id, String nombre, String email, String login, String password,double saldo) {
		super(id, nombre, email, login, password, saldo, "ADMINISTRADOR");
	}
	 
	 public void autorizarReembolso(TiqueteIndividual tiquete, double monto) {
	        Objects.requireNonNull(tiquete, "tiquete");
	        if (monto < 0) throw new IllegalArgumentException("el monto no puede ser un numero negativo");
	        tiquete.autorizarReembolso(monto);     
	        Usuario usuario = tiquete.getDuenoActual();
	        if (usuario != null) usuario.acreditarSaldo(monto);
	 }
	 public double consultarFinanzas(Finanzas finanzas) {
	        Objects.requireNonNull(finanzas, "finanzas");
	        return finanzas.calcularGananciasTiquetera(); 
	 }

}