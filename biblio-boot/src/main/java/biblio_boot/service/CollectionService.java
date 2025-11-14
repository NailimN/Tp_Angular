package biblio_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import biblio_boot.dao.IDAOCollection;
import biblio_boot.model.Collection;

@Service
public class CollectionService {

    private final IDAOCollection dao;

    public CollectionService(IDAOCollection dao) {
        this.dao = dao;
    }

    public List<Collection> findAll() {
        return dao.findAll();
    }

    public Optional<Collection> findById(Long id) {
        return dao.findById(id);
    }

    public Collection save(Collection collection) {
        return dao.save(collection);
    }

    public void delete(Long id) {
        dao.deleteById(id);
    }
}
