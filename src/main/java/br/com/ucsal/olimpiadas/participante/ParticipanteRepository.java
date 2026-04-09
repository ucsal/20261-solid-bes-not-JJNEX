package br.com.ucsal.olimpiadas.participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepository implements ParticipanteRepositoryInterface {

    private long proximoId = 1;

    private List<Participante> lista = new ArrayList<>();

    public void salvar(Participante p) {
        p.setId(proximoId++);
        lista.add(p);
    }

   public List<Participante> buscarTodos() {
    return lista;
}

   public Participante buscarPorId(long id) {

    return lista.stream()
            .filter(q -> q.getId() == id)
            .findFirst()
            .orElse(null);
    
   }
}