package tiquetes;
import Eventos.Evento;
import Usuarios.Usuario;

public abstract class Tiquete {

    protected String id;
    protected Evento evento;
    protected double precioBase;
    protected double porcentajeServicio;
    protected double cobroEmision;
    protected Usuario duenoActual;
    protected boolean transferible = true;

    public String getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public Usuario getDuenoActual() {
        return duenoActual;
    }

    public void setDuenoActual(Usuario duenoActual) {
        this.duenoActual = duenoActual;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getPorcentajeServicio() {
        return porcentajeServicio;
    }

    public double getCobroEmision() {
        return cobroEmision;
    }

    public double getPrecioTotal() {
        double recargoServicio = (precioBase * porcentajeServicio) / 100;
        return precioBase + recargoServicio + cobroEmision;
    }

    public boolean esTransferible() {
        return transferible;
    }
}