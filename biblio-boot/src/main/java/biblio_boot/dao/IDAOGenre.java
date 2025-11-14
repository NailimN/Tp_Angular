package biblio_boot.dao;

import biblio_boot.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDAOGenre extends JpaRepository<Genre, Integer> {
}
