package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import java.util.ArrayList;
import java.util.List;

public class GeradorRecomendacoes {
    
    public static List<Musica> gerarRecomendacoes(List<Musica> historico, List<Musica> catalogo) {
        List<Musica> recomendadas = new ArrayList<>();
        if (historico.isEmpty() || catalogo.isEmpty()) {
            return recomendadas;
        }

        Musica ultimaTocada = historico.get(historico.size() - 1);
        String generoFavorito = ultimaTocada.getGenero();

        for (Musica m : catalogo) {
            if (m.getGenero() != null && m.getGenero().equalsIgnoreCase(generoFavorito) && !historico.contains(m)) {
                recomendadas.add(m);
                if (recomendadas.size() >= 5) {
                    break;
                }
            }
        }
        return recomendadas;
    }
}
