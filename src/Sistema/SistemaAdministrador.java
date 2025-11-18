package Sistema;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import Eventos.Evento;
import Eventos.RepositorioEventos;
import Eventos.RepositorioVenues;
import Eventos.Venue;
import tiquetes.TiqueteIndividual;
import Usuarios.Administrador;
import Usuarios.Promotor;
import Usuarios.RepositorioUsuarios;
import Usuarios.Usuario;

public class SistemaAdministrador extends SubSistema {

    private Scanner sc = new Scanner(System.in);
    private RepositorioUsuarios repu;
    private RepositorioEventos repe;
    private RepositorioVenues repv;
    private Propuestas repp;
 
    public SistemaAdministrador(Administrador admin, RepositorioUsuarios repu, RepositorioVenues repv, RepositorioEventos repe, Propuestas repp) {
        super(admin);
        this.repu = repu;
        this.repe = repe;
        this.repv = repv;
        this.repp = repp;
    }

    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("MENÚ ADMINISTRADOR");
            System.out.println("1. Cancelar evento");
            System.out.println("2. Ver finanzas tiquetera");
            System.out.println("3. Autorizar reembolso");
            System.out.println("4. Aprobar Venue");
            System.out.println("5. Bloquear Promotor");
            System.out.println("6. Añadir Promotor");
            System.out.println("7. Aceptar Promotor");
            System.out.println("8. Aprobar cancelacion evento");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 : cancelarEvento();
                break;
                case 2 :verFinanzas();
                break;
                //case 3: aprobarReembolso();
                //break;
                case 4 : aprobarVenue();
                break;
                case 5 : bloquearPromotor();
                break;
                case 6 : añadirPromotor();
                break;
                case 7 : aceptarPromotor();
                break;
                case 8 :aprobarCancelacionEvento();
                break;
                case 9: crearVenue();
                break;
                case 0 : System.out.println("Saliendo del sistema de administración...");
                salir();
                break;
                default : System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }


    private void aprobarCancelacionEvento() {
		System.out.println(repp.getCancelacionesPropuestas());
		System.out.println("Ingresa el id del evento a cancelar: ");
		int id = sc.nextInt();
		Evento evento = repp.getEventoPropuesto(id);
		repp.aceptarCancelacionEvento(id);
		repe.cambiarEstadoEvento(evento, "CANCELADO");
	}

	private void aceptarPromotor() {
		System.out.println(repp.getPromotoresPropuestos());
		System.out.println("Ingresa el id del promotor para aceptar: ");
		int id = sc.nextInt();
		sc.nextLine();
		Promotor prom = repp.getPromotorPropuesto(id);
		repu.agregarUsuario(prom);
		repp.aceptarPromotor(id);
		
	}

	private void añadirPromotor() {
		System.out.println("Ingrese el nombre");
		String nombre = sc.nextLine();
		System.out.println("Ingrese el Email");
		String mail = sc.nextLine();
		System.out.println("Ingrese el usuario login");
		String login = sc.nextLine();
		System.out.println("Ingrese la contraseña");
		String password = sc.nextLine();
		System.out.println("Ingrese la reputacion conocida del promotor");
		int reputacion = sc.nextInt();
		System.out.println("Ingrese el NIT");
		String NIT = sc.nextLine();
		Usuario nuevoProm = new Promotor(nombre, mail, login, password, reputacion, NIT);
		repu.agregarUsuario(nuevoProm);
	}

	private void bloquearPromotor() {
		System.out.println("Ingrese el login del promotor");
		String login = sc.nextLine();
		repu.eliminarUsuario(login, "PROMOTOR");
	}

	private void cancelarEvento() {
		System.out.println(repe.getEventosActivos());
		System.out.println("Ingrese el id del evento a cancelar");
		int id = sc.nextInt();
        repe.cambiarEstadoEvento(repe.getEventoActivo(id), "CANCELADO");
    }

    private void verFinanzas() {
    	//De momento no hay finanzas
    }
    private void aprobarVenue() {
    	System.out.println(repv.getVenuesInactivos());
    	System.out.println("Ingrese el id del venue a aprobar");
		int id = sc.nextInt();
		repv.aprobarVenue(repv.getVenueActivo(id));
    }
    public void crearVenue() {
    	boolean datosCorrectos = false;
    	do {
    		System.out.println("Ingrese el tipo de venue: ");
    		String tipo = sc.nextLine();
    		System.out.println("Ingrese la latitud del venue: ");
    		int latitud = sc.nextInt();
    		sc.nextLine();
    		System.out.println("Ingrese la longitud del venue: ");
    		int longitud = sc.nextInt();
    		sc.nextLine();
    		System.out.println("Ingrese la capacidad maxima del venue: ");
    		int capacidad = sc.nextInt();
    		sc.nextLine();
    		System.out.println("Ingrese las restricciones del venue: ");
    		String restricciones = sc.nextLine();
    		System.out.println("Ingrese el nombre del venue: ");
    		String nombre = sc.nextLine();
    		try {
    			
    			Venue nuevo = new Venue(tipo, latitud, longitud, capacidad, restricciones, nombre, "ACTIVO");
    			datosCorrectos = true;
    			repv.agregarVenue(nuevo);
    		} catch (Exception e) {
    			System.out.println("La capacidad del venue se dio negativa, vuelva a intentar");
    		}
    	}while(datosCorrectos == false);	
    }

	@Override
	public void salir() {
		repu.guardar();
		repv.guardar();
		repe.guardar();
		repp.guardar();
	}

    /*public void autorizarReembolso(Tiquete_individual tiquete, double monto) {
        Objects.requireNonNull(tiquete, "El tiquete no puede ser nulo");
        if (monto <= 0) throw new IllegalArgumentException("El monto debe ser mayor a 0");

        tiquete.autorizarReembolso(monto);

        Usuario dueno = tiquete.getDuenoActual();
        if (dueno != null) {
            dueno.acreditarSaldo(monto);
            System.out.println("💰 Reembolso autorizado y acreditado a " + dueno.getNombre());
        } else {
            System.out.println("⚠️ El tiquete no tiene un dueño actual. No se acreditó saldo.");
        }
    }*/
}