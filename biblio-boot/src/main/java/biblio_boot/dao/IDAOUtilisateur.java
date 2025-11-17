package biblio_boot.dao;

import biblio_boot.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer>{
    public Optional<Utilisateur> findByUsername(String username);
}
