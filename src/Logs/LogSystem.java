package Logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogSystem {

    private static boolean activo = true; 

    public static void activarLogs() {
        activo = true;
        System.out.println("[LOG SYSTEM] Los logs han sido ACTIVADOS ");
    }

    public static void bloquearLogs() {
        activo = false;
        System.out.println("[LOG SYSTEM] Los logs han sido BLOQUEADOS ");
    }

    public static boolean estaActivo() {
        return activo;
    }

    public static void registrar(String tipo, String mensaje) {
        if (!activo) return; 
        String tiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[" + tiempo + " | " + tipo.toUpperCase() + "] " + mensaje);
    }
}

