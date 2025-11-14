package biblio_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio_boot.dao.IDAOCollection;
import biblio_boot.model.Collection;

@Service
public class CollectionService {

	@Autowired
	IDAOCollection daoCollection;

	public Collection getById(Integer id)
	{
		Optional <Collection> opt = daoCollection.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Collection> getAll()
	{
		return daoCollection.findAll();
	}

	public Collection create(Collection collection) 
	{
		return daoCollection.save(collection);
	}

	public Collection update(Collection collection) 
	{
		return daoCollection.save(collection);
	}

	public void deleteById(Integer id) 
	{
		daoCollection.deleteById(id);
	}

	public void delete(Collection collection)
	{
		daoCollection.delete(collection);
	}
}
