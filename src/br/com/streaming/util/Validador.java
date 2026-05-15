package br.com.streaming.util;

public class Validador {
    public static void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido!");
        }
    }

    public static boolean isStringValida(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
