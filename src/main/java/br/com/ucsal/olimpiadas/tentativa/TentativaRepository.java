package br.com.ucsal.olimpiadas.tentativa;

import java.util.ArrayList;
import java.util.List;

public class TentativaRepository implements TentativaRepositoryInterface {

    private List<Tentativa> lista = new ArrayList<>();
    private long proximoId = 1;

    public void salvar(Tentativa t) {

        t.setId(proximoId++);
        lista.add(t);

    }

    public List<Tentativa> buscarTodas() {

        return lista;
    }

    public Tentativa buscarPorId(long id) {

        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

    }

}
