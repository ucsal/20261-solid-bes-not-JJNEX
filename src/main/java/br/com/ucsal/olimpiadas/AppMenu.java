package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.ucsal.olimpiadas.participante.Participante;
import br.com.ucsal.olimpiadas.participante.ParticipanteService;
import br.com.ucsal.olimpiadas.prova.AplicacaoProva;
import br.com.ucsal.olimpiadas.prova.Prova;
import br.com.ucsal.olimpiadas.prova.ProvaService;
import br.com.ucsal.olimpiadas.questao.Questao;
import br.com.ucsal.olimpiadas.questao.QuestaoService;
import br.com.ucsal.olimpiadas.resposta.RespostaService;
import br.com.ucsal.olimpiadas.tabuleiro.TabuleiroPrinter;
import br.com.ucsal.olimpiadas.tentativa.Tentativa;
import br.com.ucsal.olimpiadas.tentativa.TentativaService;

public class AppMenu {

     private ParticipanteService participanteService;
    private ProvaService provaService;
    private QuestaoService questaoService;
    private RespostaService respostaService;
    private TentativaService tentativaService;
    private AplicacaoProva aplicacaoProva;
    private final Scanner in;

    public AppMenu(ParticipanteService participanteService,
                  ProvaService provaService,
                  QuestaoService questaoService,
                  RespostaService respostaService,
                  TentativaService tentativaService,
                  AplicacaoProva aplicacaoProva,
                  Scanner in) {
        this.participanteService = participanteService;
        this.provaService = provaService;
        this.questaoService = questaoService;
        this.respostaService = respostaService;
        this.tentativaService = tentativaService;
        this.aplicacaoProva = aplicacaoProva;
        this.in = in;
    }

    public void executar() {

		// seed();

		while (true) {
			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V3) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");

			switch (in.nextLine()) {
				case "1" -> cadastrarParticipante();
				case "2" -> cadastrarProva();
				case "3" -> cadastrarQuestao();
				case "4" -> aplicarProva();
				case "5" -> listarTentativas();
				case "0" -> {
					System.out.println("tchau");
					return;
				}
				default -> System.out.println("opção inválida");
			}
		}
	}

	// Cadastrar Participante

	void cadastrarParticipante() {

		System.out.print("Nome: ");
		var nome = in.nextLine();

		System.out.print("Email (opcional): ");
		var email = in.nextLine();

		if (nome == null || nome.isBlank()) {
			System.out.println("Nome inválido");
			return;
		}

		Participante p = participanteService.cadastrar(nome, email);

		System.out.println("Participante cadastrado:\n" +
				"ID - " + p.getId() +
				"\nNome - " + p.getNome());
	}

	// Cadastrar Prova

	void cadastrarProva() {
		System.out.print("Título da prova: ");
		var titulo = in.nextLine();

		if (titulo == null || titulo.isBlank()) {
			System.out.println("Título inválido");
			return;
		}

		Prova p = provaService.cadastrar(titulo);

		System.out.println("Prova criada:\n" +
				"ID - " + p.getId() +
				"\nTítulo - " + p.getTitulo());
	}

	// Cadastrar Questão

	void cadastrarQuestao() {
		if (provaService.buscarTodas().isEmpty()) {
			System.out.println("não há provas cadastradas");
			return;
		}

		var provaId = escolherProva();
		if (provaId == null)
			return;

		System.out.println("Enunciado:");
		String enunciado = in.nextLine();

		System.out.println("FEN inicial:");
		String fen = in.nextLine().trim();

		if (fen.isEmpty()) {
			fen = null;
		}

		var alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			char letra = (char) ('A' + i);
			System.out.print("Alternativa " + letra + ": ");
			alternativas[i] = letra + ") " + in.nextLine();
		}

		System.out.print("Alternativa correta (A–E): ");
		char correta;
		try {
			correta = Questao.normalizar(in.nextLine().trim().charAt(0));
		} catch (Exception e) {
			System.out.println("alternativa inválida");
			return;
		}

		Questao q = questaoService.cadastrar(provaId, enunciado, alternativas, correta, fen);

		System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
	}

	// Aplicar Prova

	void aplicarProva() {

		if (participanteService.buscarTodos().isEmpty()) {
			System.out.println("cadastre participantes primeiro");
			return;
		}

		if (provaService.buscarTodas().isEmpty()) {
			System.out.println("cadastre provas primeiro");
			return;
		}

		var participanteId = escolherParticipante();
		if (participanteId == null)
			return;

		var provaId = escolherProva();
		if (provaId == null)
			return;

		var questoes = questaoService.buscarPorProva(provaId);

		if (questoes.isEmpty()) {
			System.out.println("esta prova não possui questões cadastradas");
			return;
		}

		System.out.println("\n--- Início da Prova ---");

		List<Character> respostasMarcadas = new ArrayList<>();

		for (var q : questoes) {
			System.out.println("\nQuestão #" + q.getId());
			System.out.println(q.getEnunciado());

			if (q.getFenInicial() != null && !q.getFenInicial().isBlank()) {
				System.out.println("Posição inicial:");
				TabuleiroPrinter.imprimirTabuleiroFen(q.getFenInicial());
			} else {
				System.out.println("(Esta questão não tem tabuleiro)");
			}

			for (var alt : q.getAlternativas()) {
				System.out.println(alt);
			}

			System.out.print("Sua resposta (A–E): ");
			char marcada;

			try {
				marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
			} catch (Exception e) {
				System.out.println("resposta inválida (marcando como errada)");
				marcada = 'X';
			}

			respostasMarcadas.add(marcada);
		}

		Tentativa t = aplicacaoProva.aplicar(participanteId, provaId, respostasMarcadas);

		int nota = tentativaService.calcularNota(t);

		System.out.println("\n--- Fim da Prova ---");
		System.out.println("Nota (acertos): " + nota + " / " + t.getRespostas().size());
	}

	// Listar Tentativas

	void listarTentativas() {
		System.out.println("\n--- Tentativas ---");

		for (var t : tentativaService.buscarTodas()) {
			System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
					t.getId(),
					t.getParticipanteId(),
					t.getProvaId(),
					tentativaService.calcularNota(t),
					t.getRespostas().size());
		}
	}

	// Escolher Participante
	Long escolherParticipante() {

		System.out.println("\nParticipantes:");
		for (var p : participanteService.buscarTodos()) {
			System.out.printf("  %d) %s%n", p.getId(), p.getNome());
		}

		System.out.print("Escolha o id do participante: ");

		try {
			long id = Long.parseLong(in.nextLine());

			if (!participanteService.existeParticipante(id)) {
				System.out.println("id inválido");
				return null;
			}

			return id;

		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}

	// Escolher Prova
	Long escolherProva() {
		System.out.println("\nProvas:");
		for (var p : provaService.buscarTodas()) {
			System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
		}
		System.out.print("Escolha o id da prova: ");

		try {
			long id = Long.parseLong(in.nextLine());
			if (!provaService.existeProva(id)) {
				System.out.println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}

	// Seed(Lembrar de ver com mais atenção)

	void seed() {

		Prova p = provaService.cadastrar("Olimpíada 2026 • Nível 1 • Prova Alo");

		questaoService.cadastrar(
				p.getId(),
				"""
						Questão 1 — Mate em 1.
						É a vez das brancas.
						Encontre o lance que dá mate imediatamente.
						""",
				new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" },
				'C',
				"6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

	}
    
}
