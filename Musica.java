import java.util.ArrayList;

class Musica {
    // Atributos (Acesso padrão, sem private)
    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    void exibir() {
        System.out.printf("Musica: %s | Artista: %s | Duracao: %s | Genero: %s%n",
                this.titulo, this.artista, getDuracaoFormatada(), this.genero);
    }

    String getDuracaoFormatada() {
        int minutos = this.duracaoSegundos / 60;
        int segundosRestantes = this.duracaoSegundos % 60;
        return String.format("%d:%02d", minutos, segundosRestantes);
    }

    boolean contemTitulo(String busca) {
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}