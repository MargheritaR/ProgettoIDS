package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, String> {

    boolean existsByEmail(String email);

    Utente findByEmail(String email);

    Utente findById(int id);

}
