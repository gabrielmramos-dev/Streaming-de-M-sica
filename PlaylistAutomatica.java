import java.util.ArrayList;

public final class PlaylistAutomatica extends Playlist {
    private String criterio; // "top", "recomendadas", "recentes"

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();
        
        if (criterio.equals("top")) {
            // Simulação: adiciona as primeiras 10 músicas como se fossem as mais tocadas
            for (int i = 0; i < Math.min(10, todasMusicas.size()); i++) {
                musicas.add(todasMusicas.get(i));
            }
        } else if (criterio.equals("recomendadas")) {
            // Simulação: adiciona músicas alternadas
            for (int i = 0; i < todasMusicas.size(); i += 2) {
                musicas.add(todasMusicas.get(i));
            }
        } else if (criterio.equals("recentes")) {
            // Simulação: adiciona as últimas 5 músicas
            int start = Math.max(0, todasMusicas.size() - 5);
            for (int i = start; i < todasMusicas.size(); i++) {
                musicas.add(todasMusicas.get(i));
            }
        }
        System.out.println("✅ Playlist '" + nome + "' atualizada com " + musicas.size() + " músicas!");
    }

    public String getCriterio() { return criterio; }
}
