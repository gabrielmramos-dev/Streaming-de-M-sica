# 🎵 Sistema de Streaming de Música

## 📋 Funcionalidades
- Cadastro e gerenciamento de músicas
- Criação e gerenciamento de playlists (Personalizadas e Automáticas)
- Múltiplos tipos de usuários (Free e Premium)
- Sistema de reprodução simulado com histórico
- Geração de recomendações simples
- Sistema de downloads exclusivo para contas Premium
- Estatísticas e relatórios do sistema

## 🏗️ Arquitetura
O projeto foi estruturado em pacotes profissionais seguindo boas práticas:
- `br.com.streaming.modelo`: Contém as classes de negócio (`Musica`, `Playlist`, `Usuario`, etc).
- `br.com.streaming.servico`: Contém as interfaces de comportamento (`Reproduzivel`, `Baixavel`) e serviços (`GeradorRecomendacoes`).
- `br.com.streaming.util`: Classes utilitárias (`FormatadorTempo`, `Validador`).
- `br.com.streaming.principal`: Ponto de entrada do sistema (`StreamingMusica`).

**Conceitos de POO aplicados:**
- **Abstração**: `ItemReproducao` e `Usuario` abstraem propriedades comuns sem serem instanciados diretamente.
- **Encapsulamento**: Atributos privados e protegidos com acesso controlado via Getters/Setters e validações.
- **Herança**: `Musica` e `Playlist` herdam de `ItemReproducao`. `UsuarioFree` e `UsuarioPremium` herdam de `Usuario`.
- **Polimorfismo**: Sobrescrita de métodos como `reproduzirMusica()` com comportamentos distintos por tipo de usuário (Premium não exibe anúncios).
- **Interfaces**: Definição de contratos de comportamento com `Reproduzivel` e `Baixavel`.

## 🚀 Como Executar
Para compilar e rodar o projeto no terminal:
```bash
# Compile os arquivos (a partir da pasta raiz do projeto)
javac -d bin src/br/com/streaming/modelo/*.java src/br/com/streaming/servico/*.java src/br/com/streaming/util/*.java src/br/com/streaming/principal/*.java

# Execute a classe principal
java -cp bin br.com.streaming.principal.StreamingMusica
```

## 👤 Autor
- Nome: Gabriel Macena Ramos
- RA: Seu_RA_Aqui

## 📅 Histórico
- Checkpoints 1 a 5: Evolução das classes base, coleções, herança e regras de negócio.
- Checkpoint 6 (Final): Implementação de Interfaces, classes abstratas e reestruturação completa de Pacotes.
