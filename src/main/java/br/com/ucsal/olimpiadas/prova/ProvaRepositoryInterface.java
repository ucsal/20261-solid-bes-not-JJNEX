package br.com.ucsal.olimpiadas.prova;

import java.util.List;

public interface ProvaRepositoryInterface {
    List<Prova> buscarTodas();

    void salvar(Prova p);

    Prova buscarPorId(long id);
}