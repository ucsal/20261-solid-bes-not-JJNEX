package br.com.ucsal.olimpiadas.participante;

import java.util.List;

public class ParticipanteService {

   private ParticipanteRepositoryInterface repository;

    public ParticipanteService(ParticipanteRepositoryInterface repository) {
        this.repository = repository;
    }

    public Participante cadastrar(String nome, String email) {
        Participante p = new Participante();
        p.setNome(nome);
        p.setEmail(email);
        repository.salvar(p);
        return p;
    }

    public boolean existeParticipante(Long id) {
    return repository.buscarTodos()
            .stream()
            .anyMatch(p -> p.getId() == id);
}

public List<Participante> buscarTodos() {
    return repository.buscarTodos();
}

public Participante buscarPorId(long id) {
    return repository.buscarPorId(id);
}


}

