/**
 * 
 */
/**
 * 
 */
module Proyecto_DPO {
	requires org.junit.jupiter.api;
	requires com.google.gson;
	opens Eventos to com.google.gson;
    opens Usuarios to com.google.gson;
    opens Tiquetes to com.google.gson;
}