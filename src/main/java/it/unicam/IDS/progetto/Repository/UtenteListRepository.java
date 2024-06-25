package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteListRepository extends CrudRepository<Utente, String> {

    boolean existsByUsername(String username);

    Utente findByUsername(String username);

}
