package uniandes.dpoo.tiquetes;


import java.util.List;

import Usuarios.Usuario;

public class PaseTemporada extends TiqueteMultiple {

    public PaseTemporada(List<TiqueteIndividual> tiquetes,Usuario comprador) {
        super(tiquetes,  comprador);
    }
}
