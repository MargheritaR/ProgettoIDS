package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.StatoPendingPuntoInteresse;
import org.springframework.data.repository.CrudRepository;

public interface StatoPendingListPuntoInteresseRepository extends CrudRepository<StatoPendingPuntoInteresse, String> {

    StatoPendingPuntoInteresse findStatoPendingPuntoInteresseByNomePDI(String nomePDI);

    boolean existsStatoPendingPuntoInteresseByNomePDI(String nomePDI);

}
