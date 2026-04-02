package br.com.ucsal.olimpiadas.participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepository implements ParticipanteRepositoryInterface {

    private List<Participante> lista = new ArrayList<>();

    public void salvar(Participante p) {
        lista.add(p);
    }
}