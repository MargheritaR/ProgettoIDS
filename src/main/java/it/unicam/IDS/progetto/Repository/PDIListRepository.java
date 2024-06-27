package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.PuntoInteresse;
import org.springframework.data.repository.CrudRepository;

public interface PDIListRepository extends CrudRepository<PuntoInteresse, String> {

    PuntoInteresse findByNomePDI(String nomePDI);

}
