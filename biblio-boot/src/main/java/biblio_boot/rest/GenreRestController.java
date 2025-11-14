package biblio_boot.rest;

import biblio_boot.model.Genre;
import biblio_boot.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@CrossOrigin("*")
public class GenreRestController {

    @Autowired
    GenreService genreSrv;

    @GetMapping
    public List<Genre> allGenres() {return genreSrv.getAll();}

    @GetMapping("/{id}")
    public Genre fichegenre(@PathVariable Integer id) {return genreSrv.getById(id);}

    @PostMapping
    public Genre ajouterGenre(@RequestBody Genre genre){return (Genre) genreSrv.create(genre);}

    @PutMapping("/{id}")
    public Genre modifierGenre(@PathVariable Integer id, @RequestBody Genre genre)
    {
        genre.setId(id);
        return (Genre) genreSrv.update(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Integer id){genreSrv.deleteById(id);}
}
