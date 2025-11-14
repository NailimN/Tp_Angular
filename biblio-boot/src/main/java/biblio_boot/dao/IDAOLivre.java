package biblio_boot.dao;

import biblio_boot.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {
}
