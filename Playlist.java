import java.util.ArrayList;

class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>(); // Inicializa a lista

    void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
        System.out.println("✅ Música '" + musica.titulo + "' adicionada à playlist!");
    }

    void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            Musica removida = musicas.remove(indice);
            System.out.println("❌ Música '" + removida.titulo + "' removida.");
        } else {
            System.out.println("⚠️ Índice inválido!");
        }
    }

    void listarMusicas() {
        System.out.println("\n--- Músicas da Playlist: " + this.nome + " ---");
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).exibir();
            }
        }
    }

    int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.duracaoSegundos;
        }
        return total;
    }

    int getQuantidadeMusicas() {
        return musicas.size();
    }
}