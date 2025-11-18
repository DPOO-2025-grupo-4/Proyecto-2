package uniandes.dpoo.tiquetes;

import Eventos.Asiento;
import Eventos.Evento;
import Eventos.Localidad;
import Usuarios.Usuario;


public class TiqueteIndividual extends Tiquete {

    private static int contadorIds = 1;

    private Localidad localidad;
    private Asiento asiento; 
    private boolean reembolsado;

    public TiqueteIndividual(Evento evento,Localidad localidad,Asiento asiento,double porcentajeServicio,double cobroEmision,Usuario comprador) {
        this.id = "T-" + contadorIds++;
        this.evento = evento;
        this.localidad = localidad;
        this.asiento = asiento;
        this.duenoActual = comprador;
        this.reembolsado = false;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public boolean fueReembolsado() {
        return reembolsado;
    }

    public void autorizarReembolso(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.reembolsado = true;
    }
        @Override
        public double getPrecioTotal() {
            double precioBase = localidad.getPrecio();
            double porcentaje = evento.getPorcentajeAdicional(); // debe existir en Evento
            double emision = evento.getCobroEmision();           // debe existir en Evento

            double recargo = precioBase * porcentaje / 100.0;
            return precioBase + recargo + emision;
        }
    }