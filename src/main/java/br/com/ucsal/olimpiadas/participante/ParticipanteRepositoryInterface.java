package br.com.ucsal.olimpiadas.participante;

import java.util.List;

public interface ParticipanteRepositoryInterface {
    List<Participante> buscarTodos();
    void salvar(Participante p);
    Participante buscarPorId(long id);
}