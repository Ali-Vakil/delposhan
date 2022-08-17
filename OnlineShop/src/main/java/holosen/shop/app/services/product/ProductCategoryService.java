package holosen.shop.app.services.product;

import holosen.shop.app.entities.product.Product;
import holosen.shop.app.entities.product.ProductCategory;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.product.ProductCategoryRepository;
import holosen.shop.app.repositores.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;
    @Autowired
    private ProductRepository productRepsitory;

    public List<ProductCategory> findAll() {
        return (List<ProductCategory>) repository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public ProductCategory getById(long id) {
        Optional<ProductCategory> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public List<ProductCategory> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<ProductCategory> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public ProductCategory add(ProductCategory data) {
        return repository.save(data);
    }

    public ProductCategory update(ProductCategory data) throws DataNotFoundException {
        ProductCategory oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        oldData.setEnable(data.getEnable());
        return repository.save(oldData);

    }
    public boolean delete(long id) throws Exception {
        ProductCategory oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");

        List<Product> products = productRepsitory.findAllByCategory(id);
        if(products.size() > 0)
            throw new Exception("Please delete all products with this Category");

        repository.deleteById(id);
        return true;
    }

}

