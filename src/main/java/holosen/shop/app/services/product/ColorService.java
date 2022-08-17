package holosen.shop.app.services.product;

import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.ProductCategory;
import holosen.shop.app.entities.product.Size;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.product.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorRepository repository;

    public List<Color> getAll() {
        return repository.findAll();
    }

    public Color getById(long id) {
        Optional<Color> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Color> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Color> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }


    public Color add(Color data) {

        if (checkExist(data)) {
            return null;
        } else {
            return repository.save(data);
        }
    }

    public Color update(Color data) throws DataNotFoundException {
        Color oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");

        if (checkExist(data)) {
            return null;
        }

        oldData.setValue(data.getValue());
        oldData.setName(data.getName());
        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        Color oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        repository.deleteById(id);
        return true;
    }

    private boolean checkExist(Color data) {
        boolean exist = false;
        List<Color> oldData = getAll();
        for (int i = 0; i < oldData.size(); i++) {

            if (oldData.get(i).getName().toLowerCase().equals(data.getName().toLowerCase()) || oldData.get(i).getValue().equals(data.getValue())) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}

