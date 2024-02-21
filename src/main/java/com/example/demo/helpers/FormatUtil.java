package com.example.demo.helpers;

public class FormatUtil {
    private FormatUtil() {
    }

    public static String formatearNombre(String nombre, String apellidoPaterno, String apellidoMaterno) {
        return nombre.concat(" ").concat(apellidoPaterno).concat(" ").concat(apellidoMaterno);
    }
}
