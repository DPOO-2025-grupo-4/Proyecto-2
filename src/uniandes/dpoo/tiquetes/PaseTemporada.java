package uniandes.dpoo.tiquetes;


import java.util.List;

import Usuarios.Usuario;

public class PaseTemporada extends TiqueteMultiple {

    public PaseTemporada(List<TiqueteIndividual> tiquetes,double precioBasePaquete,double porcentajeServicio,double cobroEmision,Usuario comprador) {
        super(tiquetes, precioBasePaquete, porcentajeServicio, cobroEmision, comprador);
    }
}
