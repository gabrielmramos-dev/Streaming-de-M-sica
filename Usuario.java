import java.util.ArrayList;

class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome) {
        setNome(nome);
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public void criarPlaylist(String nomePlaylist) {
        if (nomePlaylist != null && !nomePlaylist.trim().isEmpty()) {
            Playlist nova = new Playlist(nomePlaylist);
            this.playlists.add(nova);
            System.out.println("✅ Playlist '" + nomePlaylist + "' criada!");
        }
    }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    public void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ". " + playlists.get(i).getNome() +
                        " (" + playlists.get(i).getQuantidadeMusicas() + " músicas)");
            }
        }
    }
}