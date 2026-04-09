package br.com.ucsal.olimpiadas.questao;

import java.util.ArrayList;
import java.util.List;

public class QuestaoRepository implements QuestaoRepositoryInterface {

    private List<Questao> lista = new ArrayList<>();
    private long proximoId = 1;

    public void salvar(Questao q) {
        q.setId(proximoId++);
        lista.add(q);
    }

    public List<Questao> buscarTodas() {
        return lista;
    }

    public Questao buscarPorId(long id) {
        return lista.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);

    }

}
