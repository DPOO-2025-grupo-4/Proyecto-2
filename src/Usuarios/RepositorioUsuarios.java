package Usuarios;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Eventos.Evento;
import Eventos.RepositorioEventos;

public class RepositorioUsuarios implements Serializable{

	private Map<String, Usuario> Usuarios;

	private static final String ARCHIVO = "Achivos_Persistidos/ARCHIVO_USUARIOS.dat";
	
	public RepositorioUsuarios(){
		Usuarios = new HashMap<String, Usuario>();
		
	}
	public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(this);
            System.out.println("Repositorio de usuarios guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static RepositorioUsuarios cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (RepositorioUsuarios) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new RepositorioUsuarios();
    }
    public void agregarCliente(Usuario c) {
        Usuarios.put(c.getLogin(), c);
    }

    
    public Usuario getUsuario(String login) {
    	return Usuarios.get(login);
    }
    

    public Map<String, Usuario> getUsuarios(){
        return Usuarios;
    }

    
}