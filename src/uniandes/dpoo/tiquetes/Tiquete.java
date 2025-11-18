package uniandes.dpoo.tiquetes;

import Eventos.Evento;
import Usuarios.Usuario;

public abstract class Tiquete {

    protected String id;
    protected Evento evento;
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

    public void setDuenoActual(Usuario usuario) {
        this.duenoActual = usuario;
    }

    public boolean esTransferible() {
        return transferible;
    }

    public abstract double getPrecioTotal();
}
