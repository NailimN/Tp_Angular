package biblio_boot.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import biblio_boot.model.Collection;
import biblio_boot.service.CollectionService;

@RestController
@RequestMapping("/api/collections")
public class CollectionRestController {

    private final CollectionService service;

    public CollectionRestController(CollectionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Collection> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Collection getById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public Collection create(@RequestBody Collection collection) {
        return service.save(collection);
    }

    @PutMapping("/{id}")
    public Collection update(@PathVariable Long id, @RequestBody Collection collection) {
        collection.setId(id);
        return service.save(collection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

