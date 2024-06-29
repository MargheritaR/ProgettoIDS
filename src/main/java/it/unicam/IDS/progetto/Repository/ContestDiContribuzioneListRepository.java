package it.unicam.IDS.progetto.Repository;

import it.unicam.IDS.progetto.Entita.ContestDiContribuzione;
import org.springframework.data.repository.CrudRepository;

public interface ContestDiContribuzioneListRepository extends CrudRepository<ContestDiContribuzione, String> {

    boolean existsByNomeContest(String nomeContest);

    ContestDiContribuzione findByNomeContest(String nomeContest);
}
