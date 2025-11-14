package biblio_boot.service;

import biblio_boot.dao.IDAOGenre;
import biblio_boot.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    IDAOGenre daoGenre;

    public Genre getById(Integer id) throws RuntimeException
    {
        if(id==null)
        {
            throw new RuntimeException("L'id ne peut pas etre null");
        }
        Optional<Genre> opt = daoGenre.findById(id);
        if(opt.isEmpty()) {return null;}
        else {return opt.get();}
    }

    public List<Genre> getAll()
    {
        return daoGenre.findAll();
    }

    public Genre create(Genre genre)
    {
        return daoGenre.save(genre);
    }

    public Genre update(Genre genre)
    {
        return daoGenre.save(genre);
    }

    public void deleteById(Integer id)
    {
        daoGenre.deleteById(id);
    }

    public void delete(Genre genre)
    {
        daoGenre.delete(genre);
    }
}
