import java.util.ArrayList;

class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    void criarPlaylist(String nomePlaylist) {
        Playlist nova = new Playlist();
        nova.nome = nomePlaylist;
        this.playlists.add(nova);
        System.out.println("✅ Playlist '" + nomePlaylist + "' criada para o usuário " + this.nome);
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ". " + playlists.get(i).nome +
                        " (" + playlists.get(i).getQuantidadeMusicas() + " músicas)");
            }
        }
    }
}