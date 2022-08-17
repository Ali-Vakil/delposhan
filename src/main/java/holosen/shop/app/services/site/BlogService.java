package holosen.shop.app.services.site;

import holosen.shop.app.entities.site.Blog;
import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.enums.BlogStatus;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.site.BlogRepository;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    public List<Blog> search(String search) {
        return (List<Blog>) repository.findAllByTitleContainsAndDescriptionContains(search);
    }
    public List<Blog> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate"));
        Page<Blog> all = repository.findAll(page);
        return all.toList();
    }
    public long getAllCount() {
        return repository.count();
    }
    public List<Blog> getAlldata(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate"));
        Page<Blog> all = repository.findAllByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED,new Date(),page);
        return all.toList();
    }
    public long getAllCountdata() {
        return repository.countByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED, new Date());
    }


    public Blog getById(long id) {
        Optional<Blog> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Blog add(Blog data) throws Exception {

        if(data.getTitle()==null || data.getTitle() =="")
            throw new Exception("Please fill title");
        if(data.getSubtitle()==null || data.getSubtitle() =="")
            throw new Exception("Please fill Subtitle");
        if(data.getDescription()==null || data.getDescription() =="")
            throw new Exception("Please fill Description");
        if(data.getImage()==null || data.getImage() =="")
            throw new Exception("Please fill image");


//        Date date = new Date();
//        data.setPublishDate(date);

        data.setVisitCount(0);

        return repository.save(data);
    }

    public Blog update(Blog data) throws DataNotFoundException {
        Blog oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setTitle(data.getTitle());
        oldData.setSubtitle(data.getSubtitle());
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        oldData.setPublishDate(data.getPublishDate());
        oldData.setStatus(data.getStatus());
        return repository.save(oldData);

    }
    public Blog increaseVisitCount(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+id+" not found");
        oldData.setVisitCount(oldData.getVisitCount()+ 1 );
        return repository.save(oldData);
    }

    public boolean delete(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

