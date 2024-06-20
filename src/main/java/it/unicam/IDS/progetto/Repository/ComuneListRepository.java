package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Comune;
import org.springframework.data.repository.CrudRepository;

public interface ComuneListRepository extends CrudRepository<Comune, String> {
}
