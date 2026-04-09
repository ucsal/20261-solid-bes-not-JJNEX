package br.com.ucsal.olimpiadas.prova;

import java.util.ArrayList;
import java.util.List;

public class ProvaRepository implements ProvaRepositoryInterface {

    private List<Prova> lista = new ArrayList<>();
    private long proximoId = 1;

    public void salvar(Prova p) {
        p.setId(proximoId++);
        lista.add(p);
    }

    public List<Prova> buscarTodas() {
        return lista;
    }

    public Prova buscarPorId(long id) {
        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

}