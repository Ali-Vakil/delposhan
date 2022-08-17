package holosen.shop.app.services.product;

import holosen.shop.app.entities.product.Features;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.product.FeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeaturesService {

    @Autowired
    private FeaturesRepository repository;


    public Features getById(long id) {
        Optional<Features> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Features add(Features data) {
        return repository.save(data);
    }

    public Features update(Features data) throws DataNotFoundException {
        Features oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setValue(data.getValue());
        oldData.setKey(data.getKey());
        return repository.save(oldData);

    }
    public boolean delete(long id) throws DataNotFoundException {
        Features oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

