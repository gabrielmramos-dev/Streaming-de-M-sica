import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervoGlobal = new ArrayList<>();
    static Usuario usuarioLogado = new Usuario();
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public static void main(String[] args) {
        System.out.print("Digite seu nome de usuário: ");
        usuarioLogado.nome = scanner.nextLine();

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
        System.out.println("1. Cadastrar música no acervo");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
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
        System.out.print("Título: "); m.titulo = scanner.nextLine();
        System.out.print("Artista: "); m.artista = scanner.nextLine();
        System.out.print("Duração (seg): "); m.duracaoSegundos = Integer.parseInt(scanner.nextLine());

        System.out.println("Gêneros: 1.Pop, 2.Rock, 3.Jazz, 4.Eletrônica, 5.Hip-Hop, 6.Clássica");
        int g = Integer.parseInt(scanner.nextLine());
        m.genero = GENEROS[g-1];

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
            if (m.contemTitulo(busca) || m.contemArtista(busca)) {
                m.exibir();
            }
        }
    }

    static void menuGerenciarPlaylists() {
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o número da playlist para gerenciar (ou 0 para voltar): ");
        int idx = lerOpcao() - 1;
        Playlist p = usuarioLogado.getPlaylist(idx);

        if (p != null) {
            System.out.println("\n1. Listar músicas da playlist\n2. Adicionar música do acervo\n3. Remover música\n0. Voltar");
            int subOpcao = lerOpcao();
            if (subOpcao == 1) p.listarMusicas();
            if (subOpcao == 2) {
                listarAcervo();
                System.out.print("ID da música: ");
                int idM = lerOpcao() - 1;
                if(idM >= 0 && idM < acervoGlobal.size()) p.adicionarMusica(acervoGlobal.get(idM));
            }
            if (subOpcao == 3) {
                p.listarMusicas();
                System.out.print("ID para remover: ");
                p.removerMusica(lerOpcao() - 1);
            }
        }
    }

    static void exibirEstatisticas() {
        int total = 0;
        for(Musica m : acervoGlobal) total += m.duracaoSegundos;
        System.out.println("Total de músicas no acervo: " + acervoGlobal.size());
        System.out.println("Tempo total do acervo: " + (total/60) + " minutos");
    }

    static void adicionarMusicasTeste() {
        Musica m1 = new Musica(); m1.titulo = "Bohemian Rhapsody"; m1.artista = "Queen"; m1.duracaoSegundos = 354; m1.genero = "Rock";
        acervoGlobal.add(m1);
        Musica m2 = new Musica(); m2.titulo = "Billie Jean"; m2.artista = "Michael Jackson"; m2.duracaoSegundos = 293; m2.genero = "Pop";
        acervoGlobal.add(m2);
    }
}