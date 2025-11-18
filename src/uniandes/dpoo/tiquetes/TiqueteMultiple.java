package uniandes.dpoo.tiquetes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Eventos.Evento;
import Usuarios.Usuario;

public abstract class TiqueteMultiple extends Tiquete {

    private static int contadorIds = 1;

    protected List<TiqueteIndividual> tiquetesIncluidos;

    public TiqueteMultiple(List<TiqueteIndividual> tiquetes,Usuario comprador) {
        if (tiquetes == null || tiquetes.isEmpty()) {
            throw new IllegalArgumentException("El paquete debe tener al menos un tiquete");
        }
        this.id = "TM-" + contadorIds++;
        this.tiquetesIncluidos = new ArrayList<>(tiquetes);
        this.duenoActual = comprador;
        this.evento = tiquetes.get(0).getEvento();
    }

    public List<TiqueteIndividual> getTiquetesIncluidos() {
        return Collections.unmodifiableList(tiquetesIncluidos);
    }

    @Override
    public Evento getEvento() {
        return evento;
    }

    public void transferirPaqueteA(Usuario nuevoDueno) {
        if (!transferible) {
            throw new IllegalStateException("Este paquete no es transferible");
        }
        this.duenoActual = nuevoDueno;
        for (TiqueteIndividual t : tiquetesIncluidos) {
            t.setDuenoActual(nuevoDueno);
        }
        
    }

            @Override
            public double getPrecioTotal() {
                double total = 0;
                for (TiqueteIndividual t : tiquetesIncluidos) {
                    total += t.getPrecioTotal();
                }
                return total;
            }
        }