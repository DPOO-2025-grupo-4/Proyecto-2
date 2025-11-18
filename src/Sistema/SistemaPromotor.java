package Sistema;

import java.util.Scanner;

import Eventos.Evento;
import Eventos.Localidad;
import Eventos.RepositorioEventos;
import Eventos.RepositorioVenues;
import Eventos.Venue;
import Usuarios.Promotor;

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
            //System.out.println("2. Consultar ganancias");
            //System.out.println("3. Aplicar oferta a localidad");
            //System.out.println("4- Crear paquete deluxe");
            //System.out.println("5- Comprar tiquete");
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
		boolean datosCorrectos = false;
		do {
		System.out.println("Seleccione la capacidad total del evento: ");
		int capacidad = sc.nextInt();
		sc.nextLine();
		System.out.println("Digite el tipo de Evento: ");
		String tipo = sc.nextLine();
		System.out.println("Digite la fecha del evento: (de la forma AAAA/MM/DD");
		String fecha = sc.nextLine();
		System.out.println(repv.getVenuesActivos());
		System.out.println("Digite el numero id del venue en el que desea hacer el evento, si la capacidad del venue es menor a la del evento o este no esta totalmente disponible, debe escoger otro");
		int idVenue = sc.nextInt();
		sc.nextLine();
		Venue venue = repv.getVenueActivo(idVenue);
		try {
			Evento eventoN = new Evento(capacidad, tipo, fecha, venue, (Promotor) usuario);
			System.out.println("Ingrese la cantidad de localidades que desea en el evento: ");
			int cantidad = sc.nextInt();
			sc.nextLine();
			crearLocalidad(eventoN, cantidad);
			repe.agregarEvento(eventoN);
			System.out.println("→ Evento creado correctamente.");
			datosCorrectos = true;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		}while(datosCorrectos == false);
        
    }
	private void crearLocalidad(Evento evento, int cantidad) throws Exception{
		boolean esNumerada;
		int asientoXFila = 0;
		for (int i = 0; i<= cantidad; i++) {
			System.out.println("Ingrese el precio de la localidad: ");
			double precio = sc.nextDouble();
			sc.nextLine();
			System.out.println("Ingrese las caracteristicas de la localidad: ");
			String caracter = sc.nextLine();
			System.out.println("¿La localidad tiene asientos numerados?(0-Si)(1-No)");
			int opcion = sc.nextInt();
			sc.nextLine();
			switch(opcion) {
				case 0: esNumerada = true;
				case 1: esNumerada = false;
				default: System.out.println("Opcion incorrecta");
			}
			System.out.println("Ingrese la capacidad de la localidad, procure que entre todas las localidades no sobrepasen la capacidad del evento: ");
			int capacidad = sc.nextInt();
			sc.nextLine();
			if(esNumerada = true) {
				System.out.print("Digite la cantidad de asientos por fila");
				asientoXFila = sc.nextInt();
				sc.nextLine();
			}
			Localidad localidadN = new Localidad(evento, precio, caracter, esNumerada,capacidad, asientoXFila);
			try {
				evento.agregarLocalidad(localidadN);
			} catch (Exception e) {
				throw e;
			}
		}
	}

    private void consultarGanancias() {
        System.out.println("→ Mostrando ganancias de tus eventos...");
    }

    private void aplicarOferta() {
        System.out.println("→ Oferta aplicada correctamente.");
    }

	public void salir() {
		repv.guardar();
		repe.guardar();
		
	}
}