package Sistema;

import java.util.Map;
import java.util.Scanner;

import Eventos.Evento;
import Eventos.RepositorioEventos;
import Usuarios.Cliente;
import Usuarios.RepositorioUsuarios;
import Usuarios.Usuario;
import uniandes.dpoo.tiquetes.TiqueteIndividual;

public class SistemaCliente extends SubSistema {

    private Scanner sc = new Scanner(System.in);

    public SistemaCliente(Cliente usuarioActual) {
        super(usuarioActual);
    }

    @Override
    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver eventos disponibles");
            System.out.println("2. Comprar tiquete");
            System.out.println("3. Transferir tiquete");
            System.out.println("4. Consultar saldo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 : verEventos();
                case 2 : comprarTiquete();
                case 3 : transferirTiquete();
                case 4 : consultarSaldo();
                case 0 : salir();
                		System.out.println("Saliendo del sistema de cliente...");
                default : System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void verEventos() {
        RepositorioEventos repoEventos = RepositorioEventos.cargar();
        Map<Integer, Evento> eventos = repoEventos.getVenuesActivos();

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("No hay eventos disponibles en este momento.");
            return;
        }

        System.out.println("\n=== EVENTOS DISPONIBLES ===");
        for (Evento e : eventos.values()) {
            System.out.println("ID: " + e.getIdEvento()
                    + " | Tipo: " + e.getTipoEvento()
                    + " | Fecha: " + e.getFechaEvento()
                    + " | Capacidad: " + e.getCapacidadEvento());
        }
    }

    private void comprarTiquete() {
    	
    		
    	}
    private void transferirTiquete() {
        Cliente clienteOrigen = (Cliente) usuario;

        if (clienteOrigen.getTiquet().isEmpty()) {
            System.out.println("No tienes tiquetes para transferir.");
            return;
        }

        System.out.println("\n=== TUS TIQUETES ===");
        int index = 1;
        TiqueteIndividual[] arreglo = new TiqueteIndividual[clienteOrigen.getTiquet().size()];
        int i = 0;
        for (TiqueteIndividual t : clienteOrigen.getTiquet()) {
            arreglo[i] = t;
            System.out.println(index + ". ID: " + t.getId()
                    + " | Evento: " + t.getEvento().getIdEvento()
                    + " | Precio total: " + t.getPrecioTotal());
            index++;
            i++;
        }

        System.out.print("Selecciona el número del tiquete que deseas transferir: ");
        int seleccion = sc.nextInt();
        sc.nextLine();

        if (seleccion < 1 || seleccion > arreglo.length) {
            System.out.println("Selección inválida.");
            return;
        }

        TiqueteIndividual tiqueteSeleccionado = arreglo[seleccion - 1];

        System.out.print("Ingresa el login del usuario destino: ");
        String loginDestino = sc.nextLine();

        System.out.print("Ingresa la contraseña del usuario destino: ");
        String passwordDestino = sc.nextLine();

        RepositorioUsuarios repoUsuarios = RepositorioUsuarios.cargar();
        Usuario usuarioDestino = repoUsuarios.getUsuario(loginDestino);

        if (usuarioDestino == null) {
            System.out.println("No existe un usuario con ese login.");
            return;
        }

        if (!usuarioDestino.getPassword().equals(passwordDestino)) {
            System.out.println("La contraseña del usuario destino es incorrecta.");
            return;
        }

        if (!(usuarioDestino instanceof Cliente)) {
            System.out.println("El usuario destino no es un cliente.");
            return;
        }

        Cliente clienteDestino = (Cliente) usuarioDestino;

        clienteOrigen.getTiquet().remove(tiqueteSeleccionado);
        clienteDestino.agregarTiquete(tiqueteSeleccionado);
        tiqueteSeleccionado.setDuenoActual(clienteDestino);

        System.out.println(" Tiquete " + tiqueteSeleccionado.getId() +" transferido correctamente a " + usuarioDestino.getNombre() + ".");
    }
    private void consultarSaldo() {
        Cliente c = (Cliente) usuario;
        System.out.println("Tu saldo actual es: $" + c.getSaldo());
    }

	@Override
	public void salir() {
		// TODO Auto-generated method stub
		
	}
}