package br.com.ucsal.olimpiadas.participante;


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
}

