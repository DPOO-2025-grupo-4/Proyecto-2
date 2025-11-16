  package Sistema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Promotor;
import Usuarios.RepositorioUsuarios;
import Usuarios.Usuario;

public class sistemaPrincipal {
	private static Scanner sc = new Scanner(System.in);
	//private SistemaAdministrador sistemaAdministrador;
	//private SistemaCliente sistemaCliente;
	//private SistemaPromotor sistemaPromotor;
	private SubSistema subSistema;
	private Usuario usuarioActual;
    private RepositorioUsuarios repo;

    
	public static void main(String[] args) {
		sistemaPrincipal sistema = new sistemaPrincipal();
		sistema.iniciar();
		
	}
	
	public void iniciar() {
		boolean opcionCorrecta = false;
		System.out.println("Bienvenido a BoletaMaster");
		System.out.println("Registrarse(0) o loguearse(1)");
		int opcion = sc.nextInt();
		while (opcionCorrecta != true) {
			if(opcion == 1) {
				loguearUsuario();
				opcionCorrecta = true;
			}
			if(opcion == 2) {
				//registrarUsuario();
				opcionCorrecta = true;
			}
			else {
				System.out.println("Opcion incorrecta");
			}
		}
		
		
	}
	public void registrarUsuario() {
		
	}
	public void loguearUsuario() {
		boolean datosCorrectos = false;
		while(datosCorrectos == false) {
			System.out.println("Login de usuario");
	        System.out.println("Login: "); String login = sc.nextLine();
	        System.out.println("Password: "); String password = sc.nextLine();
	        usuarioActual = repo.getUsuario(login);
	        if(usuarioActual != null) {
	        	if(usuarioActual.getPassword() == password) { //Se hace en instancias distintas pues si usuarioActual es null get pass lanzara error
	        		datosCorrectos = true;
	        	}
	        	
	        }
	        else {
	        	System.out.println("Usuario o contraseña incorrectos, intente nuevamente!!");
	        }
		}
		String rol = usuarioActual.getRol();
		
		if(rol == "Administrador") {
			subSistema = new SistemaAdministrador((Administrador) usuarioActual);
		}
		else if(rol == "Cliente") {
			subSistema = new SistemaCliente((Cliente) usuarioActual);
		}
		else if(rol == "Promotor") {
			subSistema = new SistemaPromotor((Promotor) usuarioActual);
		}
		subSistema.mostrarMenu();
    }

    
}