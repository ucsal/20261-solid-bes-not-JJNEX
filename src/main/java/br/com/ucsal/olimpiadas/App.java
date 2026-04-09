package br.com.ucsal.olimpiadas;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.participante.*;
import br.com.ucsal.olimpiadas.prova.*;
import br.com.ucsal.olimpiadas.questao.*;
import br.com.ucsal.olimpiadas.tentativa.*;
import br.com.ucsal.olimpiadas.resposta.*;


//Comentário teste

public class App {

	public static void main(String[] args) {
        ParticipanteService participanteService = new ParticipanteService(new ParticipanteRepository());
        ProvaService provaService = new ProvaService(new ProvaRepository());
        QuestaoService questaoService = new QuestaoService(new QuestaoRepository());
        RespostaService respostaService = new RespostaService(new RespostaRepository());
        TentativaService tentativaService = new TentativaService(new TentativaRepository());
        AplicacaoProva aplicacaoProva = new AplicacaoProva(questaoService, respostaService, tentativaService);

        AppMenu inicializar = new AppMenu(
            participanteService, provaService, questaoService, respostaService,
            tentativaService, aplicacaoProva, new Scanner(System.in)
        );
		inicializar.seed();
        inicializar.executar();
    }

	
}