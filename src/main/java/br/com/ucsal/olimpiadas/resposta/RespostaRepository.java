package br.com.ucsal.olimpiadas.resposta;

import java.util.ArrayList;
import java.util.List;

public class RespostaRepository implements RespostaRepositoryInterface {

     private List<Resposta> lista = new ArrayList<>();
     private long proximoId = 1;

     public void salvar(Resposta r) {
          r.setId(proximoId++);
          lista.add(r);
     }

     public List<Resposta> buscarTodas() {
          return lista;

     }

     public Resposta buscarPorId(long id) {
          return lista.stream()
                    .filter(r -> r.getId() == id)
                    .findFirst()
                    .orElse(null);
     }

}
