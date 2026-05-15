package br.com.streaming.util;

public class FormatadorTempo {
    public static String formatar(int duracaoSegundos) {
        int minutos = duracaoSegundos / 60;
        int segundosRestantes = duracaoSegundos % 60;
        return String.format("%d:%02d", minutos, segundosRestantes);
    }
}
