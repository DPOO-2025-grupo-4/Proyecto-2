package uniandes.dpoo.tiquetes;


import java.util.List;

import Usuarios.Usuario;

public class PaqueteDeluxe extends TiqueteMultiple {

    private String descripcionBeneficios;

    public PaqueteDeluxe(List<TiqueteIndividual> tiquetes,double precioBasePaquete,double porcentajeServicio,double cobroEmision,Usuario comprador,String descripcionBeneficios) {
        super(tiquetes, precioBasePaquete, porcentajeServicio, cobroEmision, comprador);
        this.descripcionBeneficios = descripcionBeneficios;
        this.transferible = false;
    }

    public String getDescripcionBeneficios() {
        return descripcionBeneficios;
    }
}
