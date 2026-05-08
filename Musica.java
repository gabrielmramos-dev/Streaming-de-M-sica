import java.util.Arrays;
import java.util.List;

public class Musica {
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    private static final List<String> GENEROS_VALIDOS = Arrays.asList("Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica");

    public Musica() {}

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        } else {
            System.out.println("Erro: Título inválido!");
        }
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
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
                    this.genero = g; // Salva com a capitalização correta da lista
                    return;
                }
            }
        }
        System.out.println("Erro: Gênero inválido!");
    }

    public void exibir() {
        System.out.printf("Musica: %s | Artista: %s | Duracao: %s | Genero: %s%n",
                this.titulo, this.artista, getDuracaoFormatada(), this.genero);
    }

    public String getDuracaoFormatada() {
        int minutos = this.duracaoSegundos / 60;
        int segundosRestantes = this.duracaoSegundos % 60;
        return String.format("%d:%02d", minutos, segundosRestantes);
    }

    public boolean contemTitulo(String busca) {
        return this.titulo != null && this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista != null && this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}