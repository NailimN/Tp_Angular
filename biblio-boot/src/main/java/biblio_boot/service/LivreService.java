package biblio_boot.service;

import biblio_boot.dao.IDAOLivre;
import biblio_boot.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired
    IDAOLivre livreDao;

    public Livre getById(Integer id){
        Optional<Livre> livre = livreDao.findById(id);

        return livre.orElse(null);
    }

    public List<Livre> getAll(){
        return livreDao.findAll();
    }

    public Livre create(Livre livre){ return livreDao.save(livre);}

    public Livre update(Livre livre){ return livreDao.save(livre);}

    public void delete(Integer id){ livreDao.deleteById(id);}

    public void deleteAll(){ livreDao.deleteAll();}




}
