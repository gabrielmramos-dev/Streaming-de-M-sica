package br.com.streaming.modelo;

import br.com.streaming.util.FormatadorTempo;
import br.com.streaming.util.Validador;
import java.util.Arrays;
import java.util.List;

public class Musica extends ItemReproducao {
    private String artista;
    private int duracaoSegundos;
    private String genero;

    private static final List<String> GENEROS_VALIDOS = Arrays.asList("Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica");

    public Musica() {}

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        super(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        if (Validador.isStringValida(artista)) {
            this.artista = artista.trim();
        } else {
            System.out.println("Erro: Artista inválido!");
        }
    }

    public int getDuracaoSegundos() { return duracaoSegundos; }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos > 0 && duracaoSegundos < 3600) {
            this.duracaoSegundos = duracaoSegundos;
        } else {
            System.out.println("Erro: Duração deve ser entre 1 e 3599 segundos!");
        }
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        if (genero != null) {
            for (String g : GENEROS_VALIDOS) {
                if (g.equalsIgnoreCase(genero)) {
                    this.genero = g;
                    return;
                }
            }
        }
        System.out.println("Erro: Gênero inválido!");
    }

    public void exibir() {
        System.out.printf("Musica: %s | Artista: %s | Duracao: %s | Genero: %s%n",
                this.titulo, this.artista, FormatadorTempo.formatar(this.duracaoSegundos), this.genero);
    }

    public boolean contemTitulo(String busca) {
        return this.titulo != null && this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista != null && this.artista.toLowerCase().contains(busca.toLowerCase());
    }

    @Override
    public void reproduzir() {
        System.out.println("▶ Reproduzindo música: " + this.titulo + " - " + this.artista);
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Música pausada: " + this.titulo);
    }

    @Override
    public void parar() {
        System.out.println("⏹ Música parada: " + this.titulo);
    }

    @Override
    public int getDuracaoTotal() {
        return this.duracaoSegundos;
    }
}
