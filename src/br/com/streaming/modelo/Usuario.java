package br.com.streaming.modelo;

import br.com.streaming.util.Validador;
import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("⚠️ Música inválida!");
            return;
        }
        musica.reproduzir();
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
            return;
        }
        for (int i = 0; i < historicoReproducao.size(); i++) {
            System.out.print((i + 1) + ". ");
            historicoReproducao.get(i).exibir();
        }
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

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (Validador.isStringValida(nome)) {
            this.nome = nome.trim();
        } else {
            System.out.println("Erro: Nome inválido!");
        }
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        try {
            Validador.validarEmail(email);
            this.email = email.trim();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Musica> getHistoricoReproducao() {
        return historicoReproducao;
    }
}
