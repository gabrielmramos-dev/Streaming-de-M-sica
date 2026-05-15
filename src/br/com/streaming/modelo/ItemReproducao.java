package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;
import br.com.streaming.util.Validador;

public abstract class ItemReproducao implements Reproduzivel {
    protected String titulo;

    public ItemReproducao() {}

    public ItemReproducao(String titulo) {
        setTitulo(titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (Validador.isStringValida(titulo)) {
            this.titulo = titulo.trim();
        } else {
            System.out.println("Erro: Título inválido!");
        }
    }
}
