package Sistema;

import java.util.Scanner;

import Eventos.RepositorioEventos;
import Eventos.RepositorioVenues;
import Eventos.Venue;
import Usuarios.Promotor;
import Usuarios.RepositorioUsuarios;

public class SistemaPromotor extends SubSistema {

    private Scanner sc = new Scanner(System.in);
    private RepositorioEventos repe;
    private RepositorioVenues repv;

    public SistemaPromotor(Promotor promotor, RepositorioEventos repe, RepositorioVenues repv) {
        super(promotor);
        this.repe = repe;
        this.repv = repv;
    }

    @Override
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ PROMOTOR ===");
            System.out.println("1. Crear evento");
            System.out.println("2. Consultar ganancias");
            System.out.println("3. Aplicar oferta a localidad");
            System.out.println("4- Crear paquete deluxe");
            System.out.println("5- Comprar tiquete");
            System.out.println("6- Proponer Venue");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 : crearEvento();
                break;
                case 2 : consultarGanancias();
                break;
                case 3 : aplicarOferta();
                break;
                //case 4 : crearPaqueteDeluxe();
                //break;
                case 5 : comprarTiquete();
                break;
                case 6: proponerVenue();
                break;
                case 0 : System.out.println("Saliendo del sistema de promotor...");
                default : System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void proponerVenue() {
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
    			
    			Venue nuevo = new Venue(tipo, latitud, longitud, capacidad, restricciones, nombre, "INACTIVO");
    			datosCorrectos = true;
    			repv.agregarVenue(nuevo);
    		} catch (Exception e) {
    			System.out.println("La capacidad del venue se dio negativa, vuelva a intentar");
    		}
    	}while(datosCorrectos == false);	
	}

	private void comprarTiquete() {
		// TODO Auto-generated method stub
		
	}

	private void crearEvento() {
        System.out.println("→ Evento creado correctamente.");
    }

    private void consultarGanancias() {
        System.out.println("→ Mostrando ganancias de tus eventos...");
    }

    private void aplicarOferta() {
        System.out.println("→ Oferta aplicada correctamente.");
    }

	@Override
	public void salir() {
		// TODO Auto-generated method stub
		
	}
}