package Usuarios;

import java.util.Objects;

import Eventos.Finanzas;
import Eventos.Oferta;
import Logs.LogSystem;
import Negociaciones.RepositorioOferta;
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
	     public void eliminarOferta(Oferta oferta, RepositorioOferta repo) {
	         repo.eliminar(oferta); 

	         LogSystem.registrar("BORRADO_OFERTA","Admin " + getLogin()+ " eliminó oferta " + oferta.getId()+ " sobre " + LogSystem.describirTiquete(oferta.getTiquete())+ " (precio inicial " + oferta.getPrecioInicial()+ ", precio actual " + oferta.getPrecioActual() + ")");
	     }

	     public void consultarLog() {
	         for (String linea : LogSystem.obtenerRegistros()) {
	             System.out.println(linea);
	         }
	     }
	 }

}