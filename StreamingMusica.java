import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervoGlobal = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    // CP4: duas variáveis separadas pois polimorfismo vem no CP5
    static UsuarioFree usuarioFree = null;
    static UsuarioPremium usuarioPremium = null;
    static String tipoUsuario = ""; // "free" ou "premium"

    public static void main(String[] args) {
        adicionarMusicasTeste();
        cadastrarUsuario();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("Até logo!");
    }

    // ===== CADASTRO =====

    static void cadastrarUsuario() {
        System.out.println("=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipo = lerOpcao();

        if (tipo == 2) {
            System.out.println("\nEscolha o plano Premium:");
            System.out.println("1. Mensal (R$ 19,90)");
            System.out.println("2. Anual (R$ 199,00)");
            System.out.println("3. Familiar (R$ 29,90)");
            System.out.print("Escolha: ");
            int planoOpcao = lerOpcao();
            String[] planos = {"Mensal", "Anual", "Familiar"};
            String plano = (planoOpcao >= 1 && planoOpcao <= 3) ? planos[planoOpcao - 1] : "Mensal";
            usuarioPremium = new UsuarioPremium(nome, email, plano);
            tipoUsuario = "premium";
            System.out.println("✅ Conta Premium (" + plano + ") criada com sucesso!");
        } else {
            usuarioFree = new UsuarioFree(nome, email);
            tipoUsuario = "free";
            System.out.println("✅ Conta Free criada com sucesso!");
        }
    }

    // ===== MENUS =====

    static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        if (tipoUsuario.equals("free")) {
            exibirMenuFree();
        } else {
            exibirMenuPremium();
        }
    }

    static void exibirMenuFree() {
        System.out.println("👤 " + usuarioFree.getNome() + " [FREE]");
        System.out.println("1. Reproduzir música");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (máx. 3)");
        System.out.println("4. Gerenciar playlists");
        System.out.println("5. Listar músicas");
        System.out.println("6. Buscar música");
        System.out.println("7. Cadastrar música");
        System.out.println("8. Estatísticas");
        System.out.println("💎 9. Fazer upgrade para Premium");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static void exibirMenuPremium() {
        System.out.println("👑 " + usuarioPremium.getNome() + " [PREMIUM - " + usuarioPremium.getPlano() + "]");
        System.out.println("1. Reproduzir música (Alta Qualidade)");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (ilimitado)");
        System.out.println("4. Gerenciar playlists");
        System.out.println("5. Listar músicas");
        System.out.println("6. Buscar música");
        System.out.println("7. Cadastrar música");
        System.out.println("8. Estatísticas");
        System.out.println("9. Baixar música");
        System.out.println("10. Ver músicas baixadas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    // ===== PROCESSAMENTO =====

    static void processarOpcao(int opcao) {
        if (tipoUsuario.equals("free")) {
            processarOpcaoFree(opcao);
        } else {
            processarOpcaoPremium(opcao);
        }
    }

    static void processarOpcaoFree(int opcao) {
        switch (opcao) {
            case 1 -> reproduzirMusica();
            case 2 -> usuarioFree.exibirHistorico();
            case 3 -> {
                System.out.print("Nome da playlist: ");
                usuarioFree.criarPlaylist(scanner.nextLine());
            }
            case 4 -> menuGerenciarPlaylists();
            case 5 -> listarAcervo();
            case 6 -> buscarMusica();
            case 7 -> cadastrarMusica();
            case 8 -> exibirEstatisticas();
            case 9 -> fazerUpgrade();
        }
    }

    static void processarOpcaoPremium(int opcao) {
        switch (opcao) {
            case 1 -> reproduzirMusica();
            case 2 -> usuarioPremium.exibirHistorico();
            case 3 -> {
                System.out.print("Nome da playlist: ");
                usuarioPremium.criarPlaylist(scanner.nextLine());
            }
            case 4 -> menuGerenciarPlaylists();
            case 5 -> listarAcervo();
            case 6 -> buscarMusica();
            case 7 -> cadastrarMusica();
            case 8 -> exibirEstatisticas();
            case 9 -> baixarMusica();
            case 10 -> usuarioPremium.listarMusicasBaixadas();
        }
    }

    // ===== AÇÕES =====

    static void reproduzirMusica() {
        if (acervoGlobal.isEmpty()) {
            System.out.println("⚠️ Nenhuma música no acervo!");
            return;
        }
        listarAcervo();
        System.out.print("Escolha o número da música: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < acervoGlobal.size()) {
            Musica musica = acervoGlobal.get(idx);
            if (tipoUsuario.equals("free")) {
                usuarioFree.reproduzirMusica(musica);
            } else {
                usuarioPremium.reproduzirMusica(musica);
            }
        } else {
            System.out.println("⚠️ Opção inválida!");
        }
    }

    static void baixarMusica() {
        if (acervoGlobal.isEmpty()) {
            System.out.println("⚠️ Nenhuma música no acervo!");
            return;
        }
        listarAcervo();
        System.out.print("Escolha o número da música para baixar: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < acervoGlobal.size()) {
            usuarioPremium.baixarMusica(acervoGlobal.get(idx));
        } else {
            System.out.println("⚠️ Opção inválida!");
        }
    }

    static void fazerUpgrade() {
        System.out.println("\n💎 UPGRADE PARA PREMIUM");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha o plano: ");
        int planoOpcao = lerOpcao();
        String[] planos = {"Mensal", "Anual", "Familiar"};
        String plano = (planoOpcao >= 1 && planoOpcao <= 3) ? planos[planoOpcao - 1] : "Mensal";

        // Cria novo usuário Premium mantendo nome e email
        usuarioPremium = new UsuarioPremium(usuarioFree.getNome(), usuarioFree.getEmail(), plano);
        tipoUsuario = "premium";
        usuarioFree = null;
        System.out.println("✅ Upgrade realizado! Bem-vindo ao Premium " + plano + "!");
    }

    static void menuGerenciarPlaylists() {
        if (tipoUsuario.equals("free")) {
            usuarioFree.listarPlaylists();
        } else {
            usuarioPremium.listarPlaylists();
        }

        System.out.print("Escolha o número da playlist (ou 0 para voltar): ");
        int idx = lerOpcao() - 1;

        Playlist p = tipoUsuario.equals("free")
                ? usuarioFree.getPlaylist(idx)
                : usuarioPremium.getPlaylist(idx);

        if (p != null) {
            System.out.println("\n1. Listar músicas\n2. Adicionar música\n3. Remover música\n0. Voltar");
            System.out.print("Escolha: ");
            int sub = lerOpcao();
            if (sub == 1) p.listarMusicas();
            if (sub == 2) {
                listarAcervo();
                System.out.print("Número da música: ");
                int idM = lerOpcao() - 1;
                if (idM >= 0 && idM < acervoGlobal.size()) p.adicionarMusica(acervoGlobal.get(idM));
            }
            if (sub == 3) {
                p.listarMusicas();
                System.out.print("Número para remover: ");
                p.removerMusica(lerOpcao() - 1);
            }
        }
    }

    static void cadastrarMusica() {
        Musica m = new Musica();
        System.out.print("Título: "); m.setTitulo(scanner.nextLine());
        System.out.print("Artista: "); m.setArtista(scanner.nextLine());
        System.out.print("Duração (seg): ");
        try { m.setDuracaoSegundos(Integer.parseInt(scanner.nextLine())); } catch (Exception e) {}
        System.out.println("Gêneros: 1.Pop, 2.Rock, 3.Jazz, 4.Eletrônica, 5.Hip-Hop, 6.Clássica");
        System.out.print("Escolha: ");
        int g = lerOpcao();
        if (g >= 1 && g <= 6) m.setGenero(GENEROS[g - 1]);
        acervoGlobal.add(m);
        System.out.println("✅ Música cadastrada!");
    }

    static void listarAcervo() {
        System.out.println("\n--- ACERVO ---");
        if (acervoGlobal.isEmpty()) {
            System.out.println("Nenhuma música no acervo.");
            return;
        }
        for (int i = 0; i < acervoGlobal.size(); i++) {
            System.out.print((i + 1) + ". ");
            acervoGlobal.get(i).exibir();
        }
    }

    static void buscarMusica() {
        System.out.print("Buscar por título ou artista: ");
        String busca = scanner.nextLine();
        boolean encontrou = false;
        for (Musica m : acervoGlobal) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) {
                m.exibir();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma música encontrada.");
    }

    static void exibirEstatisticas() {
        int total = 0;
        for (Musica m : acervoGlobal) total += m.getDuracaoSegundos();
        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Total de músicas no acervo: " + acervoGlobal.size());
        System.out.println("Tempo total do acervo: " + (total / 60) + " min " + (total % 60) + " seg");
    }

    static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    static void adicionarMusicasTeste() {
        acervoGlobal.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervoGlobal.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        acervoGlobal.add(new Musica("So What", "Miles Davis", 562, "Jazz"));
        acervoGlobal.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
    }
}
