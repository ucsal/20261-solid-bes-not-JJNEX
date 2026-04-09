package br.com.ucsal.olimpiadas.tentativa;

import java.util.List;

import br.com.ucsal.olimpiadas.resposta.Resposta;

public class TentativaService {

    private TentativaRepositoryInterface repository;

    public TentativaService(TentativaRepositoryInterface repository) {
        this.repository = repository;
    }

    public Tentativa cadastrar(long participanteId, long provaId, List<Resposta> respostas) {
        Tentativa t = new Tentativa();
        t.setParticipanteId(participanteId);
        t.setProvaId(provaId);
        t.setRespostas(respostas);
        repository.salvar(t);
        return t;
    }

    public int calcularNota(Tentativa t) {
        int acertos = 0;
        for (var r : t.getRespostas()) {
            if (r.isCorreta())
                acertos++;
        }
        return acertos;
    }

    public List<Tentativa> buscarTodas() {
        return repository.buscarTodas();
    }

    public Tentativa buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

}
