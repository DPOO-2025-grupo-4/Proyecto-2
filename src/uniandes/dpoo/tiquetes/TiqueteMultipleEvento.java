package uniandes.dpoo.tiquetes;


import java.util.List;

import Usuarios.Usuario;

public class TiqueteMultipleEvento extends TiqueteMultiple {

    public TiqueteMultipleEvento(List<TiqueteIndividual> tiquetes, double precioBasePaquete,double porcentajeServicio,double cobroEmision,Usuario comprador) {
        super(tiquetes, comprador);
    }
}
