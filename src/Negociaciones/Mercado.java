package Negociaciones;

import Logs.LogSystem;
import Tiquetes.Tiquete_individual;
import Usuarios.Cliente;
import Usuarios.Usuario;

public class Mercado {

    public void hacerContraOferta(Oferta oferta, Usuario contraparte, double nuevoPrecio) {
        double precioAnterior = oferta.getPrecioActual();
        oferta.setPrecioActual(nuevoPrecio);
        LogSystem.registrar("CONTRAOFERTA", "Usuario " + contraparte.getLogin()+ " hace contraoferta sobre oferta " + oferta.getId()  + " (" + LogSystem.describirTiquete(oferta.getTiquete()) + ")" + " precio anterior=" + precioAnterior + ", nuevo precio=" + nuevoPrecio);
    }
    public void rechazarOferta(Oferta oferta, Usuario quienRechaza) {
        LogSystem.registrar( "RECHAZO","Oferta " + oferta.getId() + " RECHAZADA por " + quienRechaza.getLogin()+ " sobre " + LogSystem.describirTiquete(oferta.getTiquete())+ " (precio actual " + oferta.getPrecioActual() + ")");
    }
    public void confirmarTransaccion(Oferta oferta, Cliente comprador) throws Exception {
        Cliente vendedor = oferta.getOferente();
        Tiquete_individual t = oferta.getTiquete();
        double precioFinal = oferta.getPrecioActual();

       LogSystem.registrar("TRANSACCION",
            "Venta concretada: " + vendedor.getLogin() + " -> " + comprador.getLogin() + " tiquete " + LogSystem.describirTiquete(t)+ " por " + precioFinal + " (precio inicial oferta " + oferta.getPrecioInicial() + ")");
    }
  

}
