package br.com.ucsal.olimpiadas.tentativa;

import java.util.List;

public interface TentativaRepositoryInterface {

    void salvar(Tentativa t);

    List<Tentativa> buscarTodas();

    Tentativa buscarPorId(long id);
}
