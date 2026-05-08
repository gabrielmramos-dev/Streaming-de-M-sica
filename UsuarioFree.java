import java.util.ArrayList;

public class UsuarioFree extends Usuario {

    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;

    // super() chama o construtor de Usuario — obrigatório ser a primeira linha
    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    // @Override: sobrescreve o método de Usuario com comportamento específico do Free
    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        // A cada 3 músicas, exibe anúncio ANTES de tocar
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        // Chama o método original de Usuario para adicionar no histórico
        super.reproduzirMusica(musica);
    }

    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de " + MAX_PLAYLISTS + " playlists atingido!");
            System.out.println("💎 Assine Premium para criar playlists ilimitadas!");
            return;
        }
        Playlist playlist = new PlaylistPersonalizada(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist '" + nome + "' criada! (" + playlists.size() + "/" + MAX_PLAYLISTS + ")");
    }

    // private: só usado internamente por esta classe
    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }

    public int getContadorReproducoes() { return contadorReproducoes; }
}
