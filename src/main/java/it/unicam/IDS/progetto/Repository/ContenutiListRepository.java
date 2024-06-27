package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.Contenuti;
import org.springframework.data.repository.CrudRepository;

public interface ContenutiListRepository extends CrudRepository<Contenuti, String> {

    Contenuti findByIdContenuto(String idContenuto);
}
