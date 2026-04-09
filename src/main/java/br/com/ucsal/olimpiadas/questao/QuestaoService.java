package br.com.ucsal.olimpiadas.questao;

import java.util.List;

public class QuestaoService {

    private QuestaoRepositoryInterface repository;

    public QuestaoService(QuestaoRepositoryInterface repository) {
        this.repository = repository;
    }

    public Questao cadastrar(long provaID, String enunciado, String[] alternativas, char alternativaCorreta,
            String fenInicial) {
        Questao q = new Questao();
        q.setProvaId(provaID);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(alternativaCorreta);
        q.setFenInicial(fenInicial);
        repository.salvar(q);
        return q;
    }

    public List<Questao> buscarTodas() {
        return repository.buscarTodas();
    }

    public Questao buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public List<Questao> buscarPorProva(long provaId) {
        return repository.buscarTodas()
                .stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }

}
