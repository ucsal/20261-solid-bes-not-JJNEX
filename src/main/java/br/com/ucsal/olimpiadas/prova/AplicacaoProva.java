package br.com.ucsal.olimpiadas.prova;

import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.olimpiadas.questao.QuestaoService;
import br.com.ucsal.olimpiadas.resposta.Resposta;
import br.com.ucsal.olimpiadas.resposta.RespostaService;
import br.com.ucsal.olimpiadas.tentativa.Tentativa;
import br.com.ucsal.olimpiadas.tentativa.TentativaService;

public class AplicacaoProva {

    private QuestaoService questaoService;
    private RespostaService respostaService;
    private TentativaService tentativaService;

    public AplicacaoProva(
            QuestaoService questaoService,
            RespostaService respostaService,
            TentativaService tentativaService) {

        this.questaoService = questaoService;
        this.respostaService = respostaService;
        this.tentativaService = tentativaService;
    }

    public Tentativa aplicar(Long participanteId, Long provaId, List<Character> respostasMarcadas) {

        var questoes = questaoService.buscarPorProva(provaId);

        if (questoes.isEmpty()) {
            throw new IllegalArgumentException("Prova sem questões");
        }

        List<Resposta> respostas = new ArrayList<>();

        for (int i = 0; i < questoes.size(); i++) {
            var q = questoes.get(i);
            char marcada = respostasMarcadas.get(i);

            Resposta r = respostaService.cadastrar(
                    q.getId(),
                    marcada,
                    q.isRespostaCorreta(marcada));

            respostas.add(r);
        }

        return tentativaService.cadastrar(participanteId, provaId, respostas);
    }

}
