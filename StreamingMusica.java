import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Musica> todasMusicas = new ArrayList<>();
    private static Usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDados();
        
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
            if (usuarioLogado == null) {
                System.out.println("1. Criar novo usuário");
                System.out.println("2. Login");
                System.out.println("3. Listar usuários");
                System.out.println("4. Exibir Estatísticas do Sistema");
                System.out.println("0. Sair");
            } else {
                System.out.println("Logged as: " + usuarioLogado.getNome() + " (" + (usuarioLogado instanceof UsuarioPremium ? "Premium" : "Free") + ")");
                System.out.println("1. Listar Músicas Disponíveis");
                System.out.println("2. Reproduzir Música");
                System.out.println("3. Minhas Playlists");
                System.out.println("4. Criar Playlist");
                System.out.println("5. Gerar Playlist Automática");
                if (usuarioLogado instanceof UsuarioPremium) {
                    System.out.println("6. Baixar Música");
                    System.out.println("7. Listar Músicas Baixadas");
                }
                System.out.println("8. Logout");
                System.out.println("0. Sair");
            }
            
            System.out.print("\nEscolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, insira um número.");
                continue;
            }

            if (usuarioLogado == null) {
                switch (opcao) {
                    case 1: criarUsuario(); break;
                    case 2: login(); break;
                    case 3: listarUsuarios(); break;
                    case 4: exibirEstatisticas(); break;
                    case 0: System.out.println("Até logo!"); break;
                    default: System.out.println("Opção inválida!");
                }
            } else {
                switch (opcao) {
                    case 1: listarMusicas(); break;
                    case 2: reproduzirMusica(); break;
                    case 3: usuarioLogado.listarPlaylists(); break;
                    case 4: criarPlaylist(); break;
                    case 5: gerarPlaylistAutomatica(); break;
                    case 6: if (usuarioLogado instanceof UsuarioPremium) baixarMusica(); break;
                    case 7: if (usuarioLogado instanceof UsuarioPremium) ((UsuarioPremium)usuarioLogado).listarMusicasBaixadas(); break;
                    case 8: usuarioLogado = null; System.out.println("Logout realizado."); break;
                    case 0: System.out.println("Até logo!"); break;
                    default: System.out.println("Opção inválida!");
                }
            }
        }
    }

    private static void inicializarDados() {
        todasMusicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        todasMusicas.add(new Musica("Blinding Lights", "The Weeknd", 200, "Pop"));
        todasMusicas.add(new Musica("Take Five", "Dave Brubeck", 324, "Jazz"));
        todasMusicas.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
        todasMusicas.add(new Musica("Stairway to Heaven", "Led Zeppelin", 482, "Rock"));
        todasMusicas.add(new Musica("Shape of You", "Ed Sheeran", 233, "Pop"));
        todasMusicas.add(new Musica("One More Time", "Daft Punk", 320, "Eletrônica"));
        
        usuarios.add(new UsuarioFree("Ana Silva", "ana@email.com"));
        usuarios.add(new UsuarioPremium("Carlos Lima", "carlos@email.com", "Anual"));
    }

    private static void criarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.println("\nTipo de conta:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        int tipo = Integer.parseInt(scanner.nextLine());
        
        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
        } else {
            System.out.print("Plano (Mensal/Anual/Familiar): ");
            String plano = scanner.nextLine();
            usuarios.add(new UsuarioPremium(nome, email, plano));
        }
        System.out.println("✅ Usuário criado!");
    }

    private static void login() {
        listarUsuarios();
        System.out.print("Escolha o ID do usuário para login: ");
        int id = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (id >= 0 && id < usuarios.size()) {
            usuarioLogado = usuarios.get(id);
            System.out.println("✅ Login realizado: " + usuarioLogado.getNome());
        } else {
            System.out.println("❌ Usuário inválido!");
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            String tipo = (u instanceof UsuarioPremium) ? "Premium" : "Free";
            System.out.println((i + 1) + ". " + u.getNome() + " (" + tipo + ")");
        }
    }

    private static void listarMusicas() {
        System.out.println("\n--- MÚSICAS DISPONÍVEIS ---");
        for (int i = 0; i < todasMusicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            todasMusicas.get(i).exibir();
        }
    }

    private static void reproduzirMusica() {
        listarMusicas();
        System.out.print("Escolha o ID da música: ");
        int id = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (id >= 0 && id < todasMusicas.size()) {
            usuarioLogado.reproduzirMusica(todasMusicas.get(id));
        } else {
            System.out.println("❌ Música inválida!");
        }
    }

    private static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        if (usuarioLogado instanceof UsuarioFree) {
            ((UsuarioFree)usuarioLogado).criarPlaylist(nome);
        } else if (usuarioLogado instanceof UsuarioPremium) {
            ((UsuarioPremium)usuarioLogado).criarPlaylist(nome);
        }
    }

    private static void gerarPlaylistAutomatica() {
        System.out.println("\n--- PLAYLISTS AUTOMÁTICAS ---");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.println("3. Adicionadas Recentemente");
        System.out.print("Escolha: ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        String nome = "";
        String criterio = "";
        switch (choice) {
            case 1: nome = "Top 10"; criterio = "top"; break;
            case 2: nome = "Recomendadas"; criterio = "recomendadas"; break;
            case 3: nome = "Recentes"; criterio = "recentes"; break;
            default: System.out.println("Opção inválida!"); return;
        }
        
        PlaylistAutomatica pa = new PlaylistAutomatica(nome, criterio);
        pa.atualizar(todasMusicas);
        // Adiciona à lista de playlists do usuário (polimorfismo: PlaylistAutomatica é uma Playlist)
        // Note: Usuario doesn't have a direct 'addPlaylist' method in the provided code, but it has a protected field 'playlists'
        // However, I should probably add a method to Usuario or just use reflection/protected access if they were in same package.
        // Wait, Playlist is protected in Usuario.
        // Let's check if I can access it. Yes, they are in the same default package.
        usuarioLogado.getPlaylists().add(pa);
        System.out.println("✅ Playlist automática adicionada às suas playlists!");
    }

    private static void baixarMusica() {
        listarMusicas();
        System.out.print("Escolha o ID da música para baixar: ");
        int id = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (id >= 0 && id < todasMusicas.size()) {
            ((UsuarioPremium)usuarioLogado).baixarMusica(todasMusicas.get(id));
        } else {
            System.out.println("❌ Música inválida!");
        }
    }

    private static void exibirEstatisticas() {
        int totalUsers = usuarios.size();
        int freeUsers = 0;
        int premiumUsers = 0;
        int totalPlays = 0;
        int freePlays = 0;
        int premiumPlays = 0;
        int totalAds = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioPremium) {
                premiumUsers++;
                premiumPlays += u.getHistoricoReproducao().size();
            } else if (u instanceof UsuarioFree) {
                freeUsers++;
                freePlays += u.getHistoricoReproducao().size();
                totalAds += ((UsuarioFree) u).getContadorReproducoes() / 3;
            }
        }
        totalPlays = freePlays + premiumPlays;

        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        System.out.println("Total de usuários: " + totalUsers);
        System.out.println("- Free: " + freeUsers + " usuários");
        System.out.println("- Premium: " + premiumUsers + " usuários");
        System.out.println("\nReproduções totais: " + totalPlays);
        if (totalPlays > 0) {
            System.out.printf("- Free: %d reproduções (%.1f%%)%n", freePlays, (freePlays * 100.0 / totalPlays));
            System.out.printf("- Premium: %d reproduções (%.1f%%)%n", premiumPlays, (premiumPlays * 100.0 / totalPlays));
        }
        System.out.println("\nAnúncios exibidos: " + totalAds);
    }
}
