package biblio_boot.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio_boot.config.SecurityConfig;
import biblio_boot.model.Auteur;
import biblio_boot.service.AuteurService;

@RestController
@RequestMapping("/api/auteur")
@CrossOrigin("*")
public class AuteurRestController {

	private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	AuteurService auteurserv;
	

	@GetMapping
	public List<Auteur> allAuteur()
	{
		log.info("Lancement de la fonction allAuteur");
		return auteurserv.getAll();
	}


	@GetMapping("/{id}")
	public Auteur ficheAuteur(@PathVariable Integer id,Auteur auteur) {
		log.info("Lancement de la fonction ficheAuteur avec l'id {}", id);
		return auteurserv.getById(id);
	}


	@PostMapping
	public Auteur ajoutAuteur(@RequestBody Auteur auteur)
	{
		log.info("Lancement de la fonction ajoutAuteur avec les données : id = {}, nom = {}, prenom = {}",
				auteur.getId(), auteur.getNom(), auteur.getPrenom());
		return auteurserv.create(auteur);
	}


	
	@PutMapping("/{id}")
	public Auteur modifierAuteur(@PathVariable Integer id,@RequestBody Auteur auteur)
	{
		log.info(
				"Lancement de la fonction modifierAuteur d'id {} avec les données : id = {}, nom = {}, prenom = {}",
				id, auteur.getId(), auteur.getNom(), auteur.getPrenom());
		auteur.setId(id);
		return (Auteur) auteurserv.update(auteur);
	}


	@DeleteMapping("/{id}")
	public void supprimerAuteur(@PathVariable Integer id) {
		log.info("Lancement de lafonction supprimerAuteur d'id {}", id);
		auteurserv.deleteById(id);
	}

	

}
