package br.com.streaming.modelo;

import java.util.ArrayList;

public abstract class Playlist extends ItemReproducao {
    protected ArrayList<Musica> musicas = new ArrayList<>();
    protected String descricao;

    public Playlist() {}

    public Playlist(String nome) {
        super(nome);
    }

    public String getNome() { return getTitulo(); }
    public void setNome(String nome) { setTitulo(nome); }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            this.musicas.add(musica);
            System.out.println("✅ Música '" + musica.getTitulo() + "' adicionada à playlist " + getNome() + "!");
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
        System.out.println("\n--- Playlist: " + this.getNome() + " ---");
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).exibir();
            }
        }
    }

    public int getQuantidadeMusicas() {
        return musicas.size();
    }

    @Override
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo playlist: " + getNome());
        if (musicas.isEmpty()) {
            System.out.println("  (Playlist vazia)");
        } else {
            for (Musica m : musicas) {
                System.out.println("  ▶ " + m.getTitulo());
            }
        }
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Playlist pausada: " + getNome());
    }

    @Override
    public void parar() {
        System.out.println("⏹ Playlist parada: " + getNome());
    }

    @Override
    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoTotal();
        }
        return total;
    }
}
