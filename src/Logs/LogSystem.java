package Logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Tiquetes.Tiquete_individual;
import Tiquetes.Tiquetes_multiples;
import uniandes.dpoo.tiquetes.PaqueteDeluxe;
import uniandes.dpoo.tiquetes.TiqueteIndividual;
import uniandes.dpoo.tiquetes.TiqueteMultipleEvento;
import Tiquetes.Paquete_deluxe;

public class LogSystem {

    private static List<String> registros = new ArrayList<>();

    public static void registrar(String tipo, String mensaje) {
        String tiempo = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String linea = "[" + tiempo + " | " + tipo.toUpperCase() + "] " + mensaje;
        registros.add(linea);
        System.out.println(linea);    
    }

    public static List<String> obtenerRegistros() {
        return Collections.unmodifiableList(registros);
    }


    public static String describirTiquete(TiqueteIndividual t) {
        if (t == null) return "(tiquete individual null)";
        String tipoEvento = "";
        String venue = "";
        if (t.getEvento() != null) {
            tipoEvento = t.getEvento().getTipoEvento();
            if (t.getEvento().getVenueAsociado() != null) {
                venue = t.getEvento().getVenueAsociado().getNombreVenue();
            }
        }
        return "TiqueteIndividual{id=" + t.getId()
                + ", asiento=" + t.getNumAsiento()
                + ", precioBase=" + t.getPrecio()
                + ", evento=" + tipoEvento
                + ", venue=" + venue + "}";
    }

    public static String describirTiquete(TiqueteMultipleEvento t) {
        if (t == null) return "(tiquetes multiples null)";
        return "TiquetesMultiples{id=" + t.getId()
                + ", cantidad=" + t.getCantidad()
                + ", precioTotal=" + t.getPrecioTotal() + "}";
    }

    public static String describirTiquete(PaqueteDeluxe p) {
        if (p == null) return "(paquete deluxe null)";
        return "PaqueteDeluxe{id=" + p.getId()
                + ", descripcion=" + p.getDescripcion()
                + ", precio=" + p.getPrecio() + "}";
    }
}

