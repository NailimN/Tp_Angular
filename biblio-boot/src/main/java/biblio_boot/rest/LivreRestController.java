package biblio_boot.rest;

import biblio_boot.model.Livre;
import biblio_boot.service.LivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
@CrossOrigin("*")
public class LivreRestController {

    private static final Logger log = LoggerFactory.getLogger(LivreRestController.class);

    @Autowired
    LivreService livreService;

    @GetMapping
    public List<Livre> getAllLivres(){
        log.info("GET /api/livre - getAllLivre() called");
        return livreService.getAll();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Integer id){
        log.info("GET /api/livre/id - getLivreById() called");
        return livreService.getById(id);
    }

    @PostMapping
    public Livre addLivre(@RequestBody Livre livre){
        log.info("POST /api/livre - addLivre() called");
        return livreService.create(livre);}

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Integer id, @RequestBody Livre livre){
        log.info("PUT /api/livre/id - updateLivre() called");
        livre.setId(id);
        return (Livre)livreService.update(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Integer id){
        log.info("DELETE /api/livre/id - deleteLivre() called");
        livreService.delete(id);
    }
}
