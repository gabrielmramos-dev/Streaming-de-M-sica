import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervoGlobal = new ArrayList<>();
    static Usuario usuarioLogado = new Usuario();
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public static void main(String[] args) {
        System.out.print("Digite seu nome de usuário: ");
        usuarioLogado.setNome(scanner.nextLine());
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarOpcaoPrincipal(opcao);
        } while (opcao != 0);
        System.out.println("Até logo!");
    }

    static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música\n2. Listar músicas\n3. Buscar música\n4. Criar playlist\n5. Gerenciar playlists\n6. Estatísticas\n0. Sair");
        System.out.print("Escolha: ");
    }

    static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    static void processarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMusica();
            case 2 -> listarAcervo();
            case 3 -> buscarMusica();
            case 4 -> {
                System.out.print("Nome da playlist: ");
                usuarioLogado.criarPlaylist(scanner.nextLine());
            }
            case 5 -> menuGerenciarPlaylists();
            case 6 -> exibirEstatisticas();
        }
    }

    static void cadastrarMusica() {
        Musica m = new Musica();
        System.out.print("Título: "); m.setTitulo(scanner.nextLine());
        System.out.print("Artista: "); m.setArtista(scanner.nextLine());
        System.out.print("Duração (seg): ");
        try { m.setDuracaoSegundos(Integer.parseInt(scanner.nextLine())); } catch(Exception e) {}

        System.out.println("Gêneros: 1.Pop, 2.Rock, 3.Jazz, 4.Eletrônica, 5.Hip-Hop, 6.Clássica");
        int g = lerOpcao();
        if (g >= 1 && g <= 6) m.setGenero(GENEROS[g-1]);
        acervoGlobal.add(m);
    }

    static void listarAcervo() {
        for (int i = 0; i < acervoGlobal.size(); i++) {
            System.out.print((i + 1) + ". ");
            acervoGlobal.get(i).exibir();
        }
    }

    static void buscarMusica() {
        System.out.print("Buscar por: ");
        String busca = scanner.nextLine();
        for (Musica m : acervoGlobal) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) m.exibir();
        }
    }

    static void menuGerenciarPlaylists() {
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o número (ou 0): ");
        int idx = lerOpcao() - 1;
        Playlist p = usuarioLogado.getPlaylist(idx);
        if (p != null) {
            System.out.println("\n1. Listar\n2. Adicionar\n3. Remover\n0. Voltar");
            int sub = lerOpcao();
            if (sub == 1) p.listarMusicas();
            if (sub == 2) {
                listarAcervo();
                System.out.print("ID da música: ");
                int idM = lerOpcao() - 1;
                if(idM >= 0 && idM < acervoGlobal.size()) p.adicionarMusica(acervoGlobal.get(idM));
            }
            if (sub == 3) {
                p.listarMusicas();
                System.out.print("ID para remover: ");
                p.removerMusica(lerOpcao() - 1);
            }
        }
    }

    static void exibirEstatisticas() {
        int total = 0;
        for(Musica m : acervoGlobal) total += m.getDuracaoSegundos();
        System.out.println("Total de músicas: " + acervoGlobal.size());
        System.out.println("Tempo total: " + (total/60) + " minutos");
    }

    static void adicionarMusicasTeste() {
        acervoGlobal.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervoGlobal.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }
}