package br.com.streaming.modelo;

public class UsuarioFree extends Usuario {

    private int contadorReproducoes;
    private static final int LIMITE_PLAYLISTS = 3;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        
        contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) {
            System.out.println("📢 [ANÚNCIO] Compre o Premium para ouvir sem interrupções!");
        }
        
        super.reproduzirMusica(musica);
    }

    public void criarPlaylist(String nome) {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            System.out.println("⚠️ Limite de playlists atingido (" + LIMITE_PLAYLISTS + "). Faça upgrade para Premium!");
            return;
        }
        Playlist playlist = new PlaylistPersonalizada(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist '" + nome + "' criada! (" + playlists.size() + "/" + LIMITE_PLAYLISTS + ")");
    }

    public int getContadorReproducoes() {
        return contadorReproducoes;
    }
}
