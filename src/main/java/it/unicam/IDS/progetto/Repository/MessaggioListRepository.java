package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Messaggio;
import org.springframework.data.repository.CrudRepository;

public interface MessaggioListRepository extends CrudRepository<Messaggio, String> {

}
