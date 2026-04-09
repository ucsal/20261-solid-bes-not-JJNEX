package br.com.ucsal.olimpiadas.prova;

import java.util.List;

public class ProvaService {

    private ProvaRepositoryInterface repository;

    public ProvaService(ProvaRepositoryInterface repository) {
        this.repository = repository;
    }

    public Prova cadastrar(String titulo) {
        Prova p = new Prova();
        p.setTitulo(titulo);
        repository.salvar(p);
        return p;
    }

    public boolean existeProva(Long id) {
        return repository.buscarTodas()
                .stream()
                .anyMatch(p -> p.getId() == id);
    }

    public List<Prova> buscarTodas() {
        return repository.buscarTodas();
    }

    public Prova buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

}
