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

import biblio_boot.model.Editeur;
import biblio_boot.service.EditeurService;

@RestController
@RequestMapping("/api/editeur")
@CrossOrigin("*")
public class EditeurRestController {

	private static final Logger log = LoggerFactory.getLogger(EditeurRestController.class);
	
	@Autowired
	EditeurService editeurSrv;


	@GetMapping
	public List<Editeur> allEditeurs()
	{
		log.info("GET /api/editeur - allEditeurs() called");
		return editeurSrv.getAll();
	}


	@GetMapping("/{id}")
	public Editeur ficheEditeur(@PathVariable Integer id, Editeur editeur) {
		log.info("GET /api/editeur/{} - ficheEditeur() called", id);
		return editeurSrv.getById(id);
	}


	@PostMapping
	public Editeur ajoutEditeur(@RequestBody Editeur editeur)
	{
		log.info("POST /api/editeur - ajoutEditeur() called");
		return editeurSrv.create(editeur);
	}


	@PutMapping("/{id}")
	public Editeur modifierEditeur(@PathVariable Integer id,@RequestBody Editeur editeur)
	{
		log.info("PUT /api/editeur/{} - modifierEditeur() called", id);
		editeur.setId(id);
		return (Editeur) editeurSrv.update(editeur);
	}


	@DeleteMapping("/{id}")
	public void supprimerEditeur(@PathVariable Integer id) {
		log.info("DELETE /api/editeur/{} - supprimerEditeur() called", id);
		editeurSrv.deleteById(id);
	}
}
