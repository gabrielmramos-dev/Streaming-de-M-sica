package br.com.streaming.modelo;

import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {
    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        this.musicas.clear(); // Limpa as músicas atuais
        if (todasMusicas == null || todasMusicas.isEmpty()) return;

        // Lógica simulada baseada no critério
        if (criterio.equals("top")) {
            // Adiciona as 3 primeiras como "top"
            for (int i = 0; i < Math.min(3, todasMusicas.size()); i++) {
                this.musicas.add(todasMusicas.get(i));
            }
        } else if (criterio.equals("recentes")) {
            // Adiciona as 3 últimas
            for (int i = Math.max(0, todasMusicas.size() - 3); i < todasMusicas.size(); i++) {
                this.musicas.add(todasMusicas.get(i));
            }
        } else {
            // "recomendadas" ou outros: adiciona músicas pares
            for (int i = 0; i < todasMusicas.size(); i += 2) {
                this.musicas.add(todasMusicas.get(i));
            }
        }
        System.out.println("🔄 Playlist automática '" + getNome() + "' atualizada! (" + getQuantidadeMusicas() + " músicas)");
    }

    // Impede adição manual
    @Override
    public void adicionarMusica(Musica musica) {
        System.out.println("⚠️ Não é possível adicionar músicas manualmente em uma playlist automática.");
    }

    // Impede remoção manual
    @Override
    public void removerMusica(int indice) {
        System.out.println("⚠️ Não é possível remover músicas manualmente de uma playlist automática.");
    }
}
