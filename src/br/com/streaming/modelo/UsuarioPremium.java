package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import java.util.ArrayList;

public class UsuarioPremium extends Usuario implements Baixavel {

    private String plano; 
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo() + " - " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    public void criarPlaylist(String nome) {
        Playlist playlist = new PlaylistPersonalizada(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist '" + nome + "' criada! (Total: " + playlists.size() + ")");
    }

    @Override
    public void baixar(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        if (estaBaixada(musica)) {
            System.out.println("ℹ️ '" + musica.getTitulo() + "' já está baixada!");
        } else {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        if (estaBaixada(musica)) {
            musicasBaixadas.remove(musica);
            System.out.println("🗑️ Download removido: " + musica.getTitulo());
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
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
