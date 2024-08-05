package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Comune;
import org.springframework.data.repository.CrudRepository;

public interface ComuneRepository extends CrudRepository<Comune, String> {
    Comune findByNomeComune(String nomeComune);
}
