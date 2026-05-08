import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano; // Mensal, Anual, Familiar
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email); // Chama construtor de Usuario
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    // @Override: sobrescreve com reprodução em alta qualidade, sem anúncios
    @Override
    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        // Premium não chama super — tem comportamento totalmente diferente
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo() + " - " + musica.getArtista());
        // historicoReproducao é protected, então conseguimos acessar diretamente
        historicoReproducao.add(musica);
    }

    public void criarPlaylist(String nome) {
        // Sem limite para Premium
        Playlist playlist = new PlaylistPersonalizada(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist '" + nome + "' criada! (Total: " + playlists.size() + ")");
    }

    public void baixarMusica(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        if (musicasBaixadas.contains(musica)) {
            System.out.println("ℹ️ '" + musica.getTitulo() + "' já está baixada!");
        } else {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        }
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicasBaixadas.get(i).exibir();
        }
    }

    public String getPlano() { return plano; }

    public void setPlano(String plano) {
        if (plano != null && !plano.trim().isEmpty()) {
            this.plano = plano.trim();
        }
    }
}
