import java.util.ArrayList;

class Playlist {
    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist() {}

    public Playlist(String nome) {
        setNome(nome);
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            this.musicas.add(musica);
            System.out.println("✅ Música '" + musica.getTitulo() + "' adicionada!");
        } else {
            System.out.println("⚠️ Não é possível adicionar uma música nula!");
        }
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            Musica removida = musicas.remove(indice);
            System.out.println("❌ Música '" + removida.getTitulo() + "' removida.");
        } else {
            System.out.println("⚠️ Índice inválido!");
        }
    }

    public void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).exibir();
            }
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return musicas.size();
    }
}