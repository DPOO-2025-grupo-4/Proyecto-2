package Eventos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioVenues implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, ArrayList<Venue>> venues;
    private static final String ARCHIVO = "Archivos_Persistidos/ARCHIVO_VENUES.dat";

    public RepositorioVenues() {
        venues = new HashMap<String, ArrayList<Venue>>();
    }

    public void agregarVenue(Venue venue) {
    	List<Venue> lista = venues.get(venue.getEstado());
    	if(lista == null) {
			ArrayList<Venue> nuevaLista = new ArrayList<Venue>();
			nuevaLista.add(venue);
			venues.put(venue.getEstado(), nuevaLista);
		}
		else {
			lista.add(venue);
		}
    }

    public void aprobarVenue(Venue venue) {
    	
    }

    public void desactivarVenue(Venue venue) {
        int id = venue.getIdVenue();
        venuesActivos.remove(id);
        venuesInactivos.put(id, venue);
    }

    public void rechazarVenue(Venue venue) {
        int id = venue.getIdVenue();
        venuesInactivos.remove(id);
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(this);
            System.out.println("Repositorio de venues guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RepositorioVenues cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (RepositorioVenues) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará un nuevo repositorio de venues.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new RepositorioVenues();
    }
    public Map<Integer, Venue> getVenuesActivos() {
        return venuesActivos;
    }

    public Map<Integer, Venue> getVenuesInactivos() {
        return venuesInactivos;
    }
}