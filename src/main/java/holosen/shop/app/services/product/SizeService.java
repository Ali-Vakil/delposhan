package holosen.shop.app.services.product;

import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.Size;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.product.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository repository;


    public Size getById(long id) {
        Optional<Size> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Size> getAll() {
        return repository.findAll();
    }


    public List<Size> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Size> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public Size add(Size data) {


        boolean isExist = checkExist(data);

        if(isExist)
        {    return null;}
        else
        {    return repository.save(data);}

    }

    public Size update(Size data) throws DataNotFoundException {
        Size oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");

        boolean isExist = checkExist(data);

        if(isExist) {
            return null;
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        Size oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        repository.deleteById(id);
        return true;
    }

    private boolean checkExist(Size data){
        List<Size> oldData = getAll();
        Boolean exist = false;

        for(int i = 0 ; i< oldData.size() ; i++ )
        {
            if(oldData.get(i).getTitle().toLowerCase().equals(data.getTitle().toLowerCase())){
                exist = true;
                break;
            }
        }

        return exist;
    }
}

