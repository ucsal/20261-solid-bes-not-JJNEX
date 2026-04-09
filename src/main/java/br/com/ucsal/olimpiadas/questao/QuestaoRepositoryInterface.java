package br.com.ucsal.olimpiadas.questao;

import java.util.List;

public interface QuestaoRepositoryInterface {

     void salvar(Questao q);

     List<Questao> buscarTodas();

     Questao buscarPorId(long id);

}
