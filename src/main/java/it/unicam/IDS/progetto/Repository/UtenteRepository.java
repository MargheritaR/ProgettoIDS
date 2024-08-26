package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, String> {

    Utente findByNome(String nome);

    Utente findByEmail(String email);

    Utente findById(int id);

}
