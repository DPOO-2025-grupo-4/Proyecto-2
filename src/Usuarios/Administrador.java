package Usuarios;

import java.util.Objects;

import Eventos.Finanzas;
import Logs.LogSystem;
import Tiquetes.Tiquete_individual;

public class Administrador extends Usuario {
	public Administrador(String id, String nombre, String email, String login, String password,double saldo) {
		super(id, nombre, email, login, password, saldo);
	}
	@Override
	public boolean esAdmin() { 
		return true; }
	 
	 public void autorizarReembolso(Tiquete_individual tiquete, double monto) {
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
	 public void activarLogs() {
	        LogSystem.activarLogs();
	        System.out.println("[ADMIN] Has activado los logs ");
	    }

	 public void bloquearLogs() {
	        LogSystem.bloquearLogs();
	        System.out.println("[ADMIN] Has bloqueado los logs ");
	    }

	 public void verEstadoLogs() {
	        if (LogSystem.estaActivo()) {
	            System.out.println("[ADMIN] Los logs están ACTIVOS ");
	        } else {
	            System.out.println("[ADMIN] Los logs están BLOQUEADOS ");
	        }
	    }

}