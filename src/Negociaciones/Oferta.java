package Negociaciones;

import Logs.LogSystem;
import Tiquetes.Tiquete_individual;
import Usuarios.Cliente;

public class Oferta {

    private String id;
    private Cliente oferente;
    private Tiquete_individual tiquete;
    private double precioInicial;
    private double precioActual;

    public Oferta(String id, Cliente oferente, Tiquete_individual tiquete, double precioInicial) {
        this.id = id;
        this.oferente = oferente;
        this.tiquete = tiquete;
        this.precioInicial = precioInicial;
        this.precioActual = precioInicial;

        
        LogSystem.registrar(
            "OFERTA",
            "Se crea oferta " + id +
            " por usuario " + oferente.getLogin() +
            " sobre " + LogSystem.describirTiquete(tiquete) +
            " con precio inicial " + precioInicial
        );
    }

    public String getId() { return id; }
    public Cliente getOferente() { return oferente; }
    public Tiquete_individual getTiquete() { return tiquete; }
    public double getPrecioInicial() { return precioInicial; }
    public double getPrecioActual() { return precioActual; }
    public void setPrecioActual(double p) { this.precioActual = p; }
}


	


