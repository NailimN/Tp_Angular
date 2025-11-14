package biblio_boot.rest;

import java.util.List;

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

import biblio_boot.model.Auteur;
import biblio_boot.service.AuteurService;

@RestController
@RequestMapping("/api/auteur")
@CrossOrigin("*")
public class AuteurRestController {
	
	@Autowired
	AuteurService auteurserv;
	

	@GetMapping
	public List<Auteur> allAuteur()
	{
		return auteurserv.getAll();
	}


	@GetMapping("/{id}")
	public Auteur ficheAuteur(@PathVariable Integer id,Auteur auteur) {
		return auteurserv.getById(id);
	}


	@PostMapping
	public Auteur ajoutAuteur(@RequestBody Auteur auteur)
	{
		return auteurserv.create(auteur);
	}


	
	@PutMapping("/{id}")
	public Auteur modifierAuteur(@PathVariable Integer id,@RequestBody Auteur auteur)
	{
		auteur.setId(id);
		return (Auteur) auteurserv.update(auteur);
	}


	@DeleteMapping("/{id}")
	public void supprimerAuteur(@PathVariable Integer id) {
		auteurserv.deleteById(id);
	}

	

}
