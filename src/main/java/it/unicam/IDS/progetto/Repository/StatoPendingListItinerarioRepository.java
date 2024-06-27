package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.StatoPendingItinerario;
import org.springframework.data.repository.CrudRepository;

public interface StatoPendingListItinerarioRepository extends CrudRepository<StatoPendingItinerario, String> {

    StatoPendingItinerario  findStatoPendingItinerarioByNomeItinerario(String nomeItinerario);

    boolean existsByNomeItinerario(String nomeItinerario);
}
