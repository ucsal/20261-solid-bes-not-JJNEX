package br.com.ucsal.olimpiadas.resposta;

import java.util.List;

public class RespostaService {

    private RespostaRepositoryInterface repository;

    public RespostaService(RespostaRepositoryInterface repository) {
        this.repository = repository;
    }

    public Resposta cadastrar(long questaoId, char alternativaMarcada, boolean correta) {
        Resposta r = new Resposta();
        r.setQuestaoId(questaoId);
        r.setAlternativaMarcada(alternativaMarcada);
        r.setCorreta(correta);
        repository.salvar(r);
        return r;
    }

    public List<Resposta> buscarTodas() {
        return repository.buscarTodas();
    }

    public Resposta buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

}
