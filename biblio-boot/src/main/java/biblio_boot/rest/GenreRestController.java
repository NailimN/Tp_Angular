package biblio_boot.rest;

import biblio_boot.model.Genre;
import biblio_boot.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@CrossOrigin("*")
public class GenreRestController {

    private static final Logger log = LoggerFactory.getLogger(GenreRestController.class);

    @Autowired
    GenreService genreSrv;

    @GetMapping
    public List<Genre> allGenres() {
        log.info("GET /api/genre - allGenres() called");
        return genreSrv.getAll();
    }

    @GetMapping("/{id}")
    public Genre fichegenre(@PathVariable Integer id) {
        log.info("GET /api/genre/{} - ficheGenre() called", id);
        return genreSrv.getById(id);
    }

    @PostMapping
    public Genre ajouterGenre(@RequestBody Genre genre){
        log.info("POST /api/genre - ajouterGenre() called");
        return (Genre) genreSrv.create(genre);
    }

    @PutMapping("/{id}")
    public Genre modifierGenre(@PathVariable Integer id, @RequestBody Genre genre)
    {
        log.info("PUT /api/genre/{} - modifierGenre() called with genre: {}", id, genre);
        genre.setId(id);
        return (Genre) genreSrv.update(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Integer id){
        log.info("DELETE /api/genre/{} - deleteGenre() called", id);
        genreSrv.deleteById(id);
    }
}
