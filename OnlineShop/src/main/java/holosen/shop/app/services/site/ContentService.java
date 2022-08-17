package holosen.shop.app.services.site;

import holosen.shop.app.entities.site.Content;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.site.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repository;

    public List<Content> findByKey(String key) {
        return (List<Content>) repository.findFirstByKey(key);
    }

    public List<Content> getAll(Integer pageSize, Integer pageNumber){
        Pageable page = PageRequest.of(pageNumber,pageSize,Sort.by("id"));
        Page<Content> all = repository.findAll(page);
        return all.toList();
    }
    public List<Content> getAllData(){
        return repository.findAll();
    }
    public Content getById(long id) {
        Optional<Content> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public long getAllCount(){
        return repository.count();
    }

    public Content add(Content data) {
        data.setValue(data.getValue().substring(0,4100));
        return repository.save(data);
    }

    public Content update(Content data) throws DataNotFoundException {
        Content oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setValue(data.getValue().substring(0,4100));
        oldData.setKey(data.getKey());
        return repository.save(oldData);

    }
    public boolean delete(long id) throws DataNotFoundException {
        Content oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

