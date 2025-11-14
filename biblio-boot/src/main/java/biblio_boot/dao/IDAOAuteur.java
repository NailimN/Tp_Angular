package biblio_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio_boot.model.Auteur;

public interface IDAOAuteur extends JpaRepository<Auteur,Integer> {
	
	

}
