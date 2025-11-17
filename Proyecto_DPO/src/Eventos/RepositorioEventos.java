package Eventos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;



public class RepositorioEventos{
	public Map<String, Map<Integer, Evento>> eventos;
	private static final String ARCHIVO = "Archivos_Persistidos/ARCHIVO_EVENTOS.json";
	
	public RepositorioEventos(){
		eventos = new HashMap<String, Map<Integer, Evento>>();
	}
	public void agregarEvento(Evento evento){
		Map<Integer,Evento> estado = eventos.get(evento.getEstado());
		if(estado == null) {
			Map<Integer,Evento> nuevoMapa = new HashMap<Integer,Evento>();
			nuevoMapa.put(evento.getIdEvento(), evento);
			eventos.put(evento.getEstado(), nuevoMapa);
		}
		else {
			estado.put(evento.getIdEvento(), evento);
		}
		
	}
	public void cambiarEstadoEvento(Evento evento, String estado) {
		Map<Integer,Evento> tipo = eventos.get(evento.getEstado());
		tipo.remove(evento.getIdEvento());
		evento.setEstado(estado);
		agregarEvento(evento);
		
	}
	
    public void guardar() {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Eventos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RepositorioEventos cargar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Gson gson = new Gson();
            RepositorioEventos repo = gson.fromJson(reader, RepositorioEventos.class);
            if (repo == null) {
                repo = new RepositorioEventos();
            }
            return repo;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo eventos.json no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RepositorioEventos();
    }
    public Map<String, Map<Integer,Evento>> getEventos() {
        return eventos;
    }
    public List<Evento> getEventosActivos(){
    	Map<Integer,Evento> activos = eventos.get("ACTIVO");
    	List<Evento> rst = new ArrayList<Evento>(activos.values()); 
    	return rst;
    }
    public Evento getEventoActivo(int id) {
    	Map<Integer,Evento> activos = eventos.get("ACTIVO");
    	Evento rst = activos.get(id);
    	return rst;
    }

}
