package Negociaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioOferta {

    private List<Oferta> ofertas;

    public RepositorioOferta() {
        this.ofertas = new ArrayList<>();
    }

    public void agregar(Oferta oferta) {
        if (oferta == null) return;
        ofertas.add(oferta);
    }

    public boolean eliminar(Oferta oferta) {
        if (oferta == null) return false;
        return ofertas.remove(oferta);
    }

    public boolean eliminarPorId(String id) {
        if (id == null) return false;
        return ofertas.removeIf(o -> id.equals(o.getId()));
    }

    public Oferta buscarPorId(String id) {
        if (id == null) return null;
        for (Oferta o : ofertas) {
            if (id.equals(o.getId())) {
                return o;
            }
        }
        return null;
    }

    public List<Oferta> getOfertas() {
        return Collections.unmodifiableList(ofertas);
    }
}

