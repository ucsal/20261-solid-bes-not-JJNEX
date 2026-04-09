package br.com.ucsal.olimpiadas.resposta;

import java.util.List;

public interface RespostaRepositoryInterface {

    void salvar(Resposta r);

    List<Resposta> buscarTodas();

    Resposta buscarPorId(long id);

}
