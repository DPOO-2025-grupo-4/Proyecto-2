package Sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Eventos.Evento;
import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Promotor;
import Usuarios.RepositorioUsuarios;
import Usuarios.Usuario;

public class Propuestas {


	public TreeMap<Integer, Promotor> promotoresPropuestos;
	public TreeMap<Integer, Evento> eventoPropuestasCancelacion;
	//public TreeMap<String, Tiquete> reembolsoPropuesta;
	

	private static final String ARCHIVO = "Archivos_Persistidos/ARCCHIVO_PROPUESTAS.json";
	
	public Propuestas(){
		promotoresPropuestos = new TreeMap<Integer, Promotor>();
		eventoPropuestasCancelacion = new TreeMap<Integer, Evento>();
	}

    public void guardar() {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Propuestas guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Propuestas cargar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Gson gson = new Gson();
            Propuestas repo = gson.fromJson(reader, Propuestas.class);
            if (repo == null) {
                repo = new Propuestas();
            }
            return repo;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo usuarios.json no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Propuestas();
    }
    
    public void agregarPropuestapromotor(Promotor promotor) {
    	promotoresPropuestos.put(promotor.getId(), promotor);
    }
    public void pedirCancelacionEvento(Evento evento) {
    	eventoPropuestasCancelacion.put(evento.getIdEvento(), evento);
    }
    
    public void aceptarPromotor(Integer promotor) {
    	promotoresPropuestos.remove(promotor);
    }
    public void aceptarCancelacionEvento(Integer evento) {
    	eventoPropuestasCancelacion.remove(evento);
    }
    public List<Promotor> getPromotoresPropuestos(){
    	List<Promotor> lista = new ArrayList<Promotor>(promotoresPropuestos.values());
    	return lista;
    }
    public List<Evento> getCancelacionesPropuestas(){
    	List<Evento> lista = new ArrayList<Evento>(eventoPropuestasCancelacion.values());
    	return lista;
    }
    public Promotor getPromotorPropuesto(Integer prom) {
    	return promotoresPropuestos.get(prom);
    	
    }
    public Evento getEventoPropuesto(Integer evento) {
    	return eventoPropuestasCancelacion.get(evento);
    	
    }
    
    
    
    
}
