package biblio_boot.rest;

import biblio_boot.model.Livre;
import biblio_boot.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@CrossOrigin("*")
public class LivreRestController {

    @Autowired
    LivreService livreService;

    @GetMapping
    public List<Livre> getAllLivres(){
        return livreService.getAll();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Integer id){
        return livreService.getById(id);
    }

    @PostMapping
    public Livre addLivre(@RequestBody Livre livre){ return livreService.create(livre);}

    @PutMapping
    public Livre updateLivre(@PathVariable Integer id, @RequestBody Livre livre){
        livre.setId(id);
        return (Livre)livreService.update(livre);
    }

    @DeleteMapping
    public void deleteLivre(@PathVariable Integer id){ livreService.delete(id);}
}
