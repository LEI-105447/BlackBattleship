# BlackBattleship

## Membros do grupo

**Grupo:** GrupoTp03_LEI-PL-10

| Número | Nome | Curso |
|---:|---|---|
| 83590 | Valentyna Rusinova | Engenharia Informática (PL) |
| 105447 | Rodrigo Silva | Engenharia Informática (PL) |
| 128782 | João Mota | Engenharia Informática (PL) |
| 128791 | João Nunes | Engenharia Informática (PL) |

## Product Backlog

Backlog (Scrum, user stories numeradas) do jogo de batalha naval online https://papergames.io/en/battleship, elaborado a partir da consulta direta das suas páginas: página do jogo, partida contra o robot, definições de jogo, criação de torneios, os meus torneios, amigos e histórico.

Prioridade (MoSCoW): **M** = Must (essencial), **S** = Should (importante), **C** = Could (desejável).

### Épico A – Entrar e começar a jogar

- **US1 (M).** Como jogador, quero abrir a página do jogo e ver os modos disponíveis, para escolher como quero jogar.
  - Aceitação: aparecem os modos “Play with a friend”, “Play vs robot”, “Create tournament” e “Play online”.
- **US2 (M).** Como convidado, quero indicar um nickname, para poder jogar sem criar conta.
  - Aceitação: ao escolher um modo de jogo aparece o diálogo “Who are you?”; ao confirmar o nickname com “Continue” entro na sala de jogo.
- **US3 (M).** Como jogador, quero começar uma partida contra o robot, para jogar a qualquer hora.
  - Aceitação: são apresentados o meu tabuleiro (“Your boats”) e o do adversário, e o botão “Abort game”.
- **US4 (S).** Como jogador, quero ver o ranking diário e o ranking dos últimos 30 dias, para saber quem está a jogar melhor.
  - Aceitação: a página do jogo lista jogadores com pontuação nos dois rankings.

### Épico B – Jogar a partida

- **US5 (M).** Como jogador, quero disparar sobre uma casa do tabuleiro do adversário, para tentar afundar os seus navios.
  - Aceitação: depois do clique, a casa fica marcada.
- **US6 (M).** Como jogador, quero ver o meu tabuleiro com a frota colocada aleatoriamente (navios de 5, 4, 3, 3 e 2 casas), para saber onde estão os meus navios.
  - Aceitação: o tabuleiro 10×10 “Your boats” mostra os 5 navios.
- **US7 (M).** Como jogador, quero que uma casa disparada fique marcada como falha ou acerto, para saber o resultado de cada disparo.
  - Aceitação: a casa mostra a marca correspondente (falha ou fogo).
- **US8 (S).** Como jogador, quero voltar a disparar quando acerto num navio, para continuar a atacar. *(regra descrita na página do jogo)*
  - Aceitação: após um acerto, continua a ser a minha vez.
- **US9 (S).** Como jogador, quero ver de quem é a vez de jogar e o tempo restante, para saber quando devo disparar.
  - Aceitação: é apresentada a mensagem “It's your turn!” e os relógios de cada jogador.
- **US10 (C).** Como jogador, quero usar o míssil grande (uma cruz de 5 casas, com 2 unidades no início), para atingir várias casas de uma vez.
  - Aceitação: a barra de armas mostra o míssil grande com o número 2; ao selecioná-lo e disparar, ficam marcadas 5 casas e sobra 1 unidade.
- **US11 (C).** Como jogador, quero apanhar prendas no tabuleiro ao acertar nelas, para ganhar armas extra. *(descrito na página do jogo)*
  - Aceitação: ao acertar numa prenda, a arma é acrescentada às minhas armas.
- **US12 (S).** Como jogador, quero desistir de uma partida (“Abort game” no início, “Resign” depois de jogar), para terminar o jogo quando já não quero continuar.
  - Aceitação: o botão pede confirmação (“Are you sure you want to continue?”) e, ao confirmar, a partida termina.
- **US13 (S).** Como jogador, quero ver o resultado final da partida, para saber quem ganhou.
  - Aceitação: no fim é apresentado o resultado (por exemplo 0–1) com os botões “Play again!” e “Leave room”.
- **US14 (C).** Como jogador, quero enviar emojis durante a partida, para comunicar com o adversário.
  - Aceitação: o botão de emojis da sala permite escolher e enviar um emoji.

### Épico C – Jogar com outras pessoas

- **US15 (M).** Como jogador, quero escolher “Play with a friend” e obter um link de convite, para jogar com alguém que conheço.
  - Aceitação: é gerado um link único para a sala.
- **US16 (S).** Como jogador, quero partilhar o link de convite, para que o meu amigo entre na partida com um clique e sem conta. *(descrito na página do jogo)*
  - Aceitação: o amigo abre o link e entra na sala sem se registar.
- **US17 (S).** Como jogador, quero configurar o jogo (tempo por jogada, minutos por jogador, quem joga primeiro, tempo ilimitado), para adaptar as regras à partida.
  - Aceitação: as “Game settings” mostram estas opções (por omissão: 40 segundos por jogada, 3 minutos por jogador, primeiro jogador aleatório) e permitem guardá-las.
- **US18 (S).** Como jogador, quero escolher “Play online” para ser emparelhado com um jogador aleatório, para jogar contra pessoas de todo o mundo. *(fluxo não percorrido, para não entrar em fila com outros jogadores)*
  - Aceitação: é-me atribuído um adversário e a partida começa.
- **US19 (C).** Como jogador, quero ver a minha lista de amigos e os convites pendentes, para jogar com eles mais facilmente.
  - Aceitação: a página “Friends” apresenta os convites pendentes e os amigos (ou a mensagem de que ainda não tenho amigos).

### Épico D – Torneios

- **US20 (S).** Como jogador, quero abrir “Create tournament”, para organizar um campeonato.
  - Aceitação: abre a página “Tournament creation”.
- **US21 (S).** Como organizador, quero dar um nome ao torneio, para os amigos o reconhecerem.
  - Aceitação: o campo “Tournament name” aceita o nome e o torneio apresenta-o.
- **US22 (S).** Como organizador, quero escolher o jogo e o modo do torneio (“Leaderboard” ou “Bracket”), para definir o formato.
  - Aceitação: o jogo por omissão é Battleship e os dois modos podem ser escolhidos.
- **US23 (C).** Como organizador, quero personalizar as regras dos jogos (melhor de 3, minutos por jogador, penalização), para ajustar a competição.
  - Aceitação: “Match settings” permite alterar as opções (por omissão: melhor de 3, 3 minutos, 15 pontos de penalização).
- **US24 (S).** Como organizador, quero criar o torneio e partilhar um link único, para convidar os participantes. *(plano gratuito: até 4 jogadores, que podem jogar sem conta)*
  - Aceitação: depois de criar, é gerado o link do torneio.
- **US25 (C).** Como jogador, quero ver os meus torneios (“My tournaments”), para os acompanhar.
  - Aceitação: a página lista os meus torneios ou mostra “You don't have any tournaments yet”.

### Épico E – Histórico das partidas

- **US26 (C).** Como jogador, quero consultar o histórico das minhas partidas de batalha naval, para rever resultados anteriores.
  - Aceitação: a página “History” lista jogadores, resultado, jogo e data de cada partida.

### Épico F – Complementos da partida e dos torneios

- **US27 (C).** Como jogador, quero trocar mensagens com o adversário na caixa “Write a message…” da sala, para comunicar durante a partida.
  - Aceitação: a sala tem uma caixa de mensagem e o botão de enviar.
- **US28 (C).** Como jogador, quero voltar a jogar no fim da partida (“Play again!”), para pedir uma revanche.
  - Aceitação: o ecrã final mostra o botão “Play again!”.
- **US29 (C).** Como jogador, quero sair da sala (“Leave room”), para abandonar uma sala sem jogar.
  - Aceitação: o botão “Leave room” fecha a sala e regressa à página do jogo.
- **US30 (C).** Como organizador, quero acrescentar uma descrição ao torneio (“+ Add a description”) e escolher o nome entre as sugestões (“Try: Battleship Friday Cup…”), para o preencher mais depressa.
  - Aceitação: a página de criação mostra as sugestões de nome e a opção de descrição.
- **US31 (S).** Como jogador, quero ler as regras do jogo na própria página, para saber como se joga antes de começar.
  - Aceitação: a página do jogo mostra a secção "Rules of Battleship game online", com a grelha 10×10 e a frota de cinco navios (5, 4, 3, 3 e 2 casas).
- **US32 (M).** Como jogador, quero ver dois tabuleiros de 10×10 casas durante a partida, para acompanhar a minha frota e os meus disparos.
  - Aceitação: a partida mostra dois tabuleiros, cada um com 100 casas.
- **US33 (M).** Como jogador, quero ver os disparos do adversário marcados no meu tabuleiro, para saber onde me atingiu.
  - Aceitação: depois de eu falhar um disparo, o robot dispara e aparecem marcas novas no tabuleiro “Your boats”.
- **US34 (S).** Como jogador, quero usar o míssil simples tantas vezes quantas quiser, para poder disparar sempre, mesmo sem armas especiais.
  - Aceitação: na barra de armas o míssil simples vem selecionado e não tem contador.
- **US35 (S).** Como jogador, quero afundar um navio quando todas as suas casas são atingidas, para me aproximar da vitória. *(regra descrita na página do jogo)*
  - Aceitação: ao atingir todas as casas de um navio (5, 4, 3, 3 ou 2 casas), o navio fica afundado.
- **US36 (C).** Como jogador, quero usar a chuva de mísseis, para atingir até 7 casas aleatórias de uma vez. *(arma obtida em prendas; descrita na página do jogo)*
  - Aceitação: ao usar a chuva de mísseis, caem até 7 disparos em casas aleatórias do tabuleiro do adversário.
- **US37 (C).** Como jogador, quero usar o míssil nuclear, para atingir uma área de 13 casas. *(arma obtida em prendas; descrita na página do jogo)*
  - Aceitação: ao usar o míssil nuclear, ficam marcadas 13 casas à volta da casa escolhida.

## Viabilidade (verificada no site em 21/11/2026)

| Resultado | User stories |
|---|---|
| Confirmada no site e automatizável | US1, US2, US3, US4, US5, US6, US7, US8, US9, US10, US12, US14, US15, US17, US19, US20, US21, US22, US23, US25, US26, US31, US32, US33, US34 |
| Confirmada com cuidado | US13 (o resultado final depende de quem ganha); US24 (“Create and share” está ativo, mas não foi premido para não criar um torneio real) |
| Vista na interface, ainda sem teste automático | US27, US28, US29, US30 |
| Não verificada | US11 (prendas), US35 (afundar um navio), US36 e US37 (armas que só se obtêm nas prendas), US16 (precisa de uma segunda sessão para entrar pelo link), US18 (“Play online” emparelha com jogadores reais) |

Notas para quem escrever os testes:
- Os navios mudam em cada jogo. Uma casa disparada fica marcada como **falha** (`svg` com a classe `no-hit`) ou como **acerto** (`div` com a classe `hit fire`); o teste deve aceitar ambas.
- No início da partida aparece um aviso animado “It's your turn!”; o primeiro clique deve ser repetido até a casa ficar marcada.
- O aviso de cookies (“Do not consent”) pode aparecer à entrada.
