package Sistema;

import java.util.Objects;
import java.util.Scanner;
import Tiquetes.Tiquete_individual;
import Usuarios.Administrador;
import Usuarios.Usuario;

public class SistemaAdministrador extends SubSistema {

    private Scanner sc = new Scanner(System.in);

    public SistemaAdministrador(Administrador admin) {
        super(admin);
    }

    @Override
    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("MENÚ ADMINISTRADOR");
            System.out.println("1. Cancelar evento");
            System.out.println("2. Ver finanzas generales");
            System.out.println("3. Autorizar reembolso");
            System.out.println("4. AprobarVenue");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> cancelarEvento();
                case 2 -> verFinanzas();
                case 3 -> ejecutarAutorizarReembolso();
                case 4 -> aprobarVenue();
                case 0 -> System.out.println("Saliendo del sistema de administración...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }


    private void cancelarEvento() {
        System.out.println("→ Cancelando evento...");
    }

    private void verFinanzas() {
        System.out.println("→ Mostrando reporte de finanzas...");
    }
    private void aprobarVenue() {
    	
    }

  
    private void ejecutarAutorizarReembolso() {
        System.out.print("Ingrese ID del tiquete a reembolsar: ");
        String id = sc.nextLine();


        Tiquete_individual tiquete = null;

        if (tiquete == null) {
            System.out.println("No se encontró un tiquete con el ID proporcionado.");
            return;
        }

        System.out.print("Ingrese el monto a reembolsar: ");
        double monto = sc.nextDouble();
        sc.nextLine();

        try {
            autorizarReembolso(tiquete, monto);
            System.out.println("✅ Reembolso autorizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al autorizar reembolso: " + e.getMessage());
        }
    }


    public void autorizarReembolso(Tiquete_individual tiquete, double monto) {
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
    }
}