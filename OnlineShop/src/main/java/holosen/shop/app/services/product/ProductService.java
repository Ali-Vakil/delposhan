package holosen.shop.app.services.product;

import holosen.shop.app.entities.product.Features;
import holosen.shop.app.entities.product.Product;
import holosen.shop.app.entities.product.ProductCategory;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.helper.uiModels.people.ProductVM;
import holosen.shop.app.repositores.product.FeaturesRepository;
import holosen.shop.app.repositores.product.ProductRepository;
import jdk.jfr.Category;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private FeaturesService featuresService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductCategoryService productCategoryService;


    public List<Product> findAllbycategory(long CategoryId) {
        return (List<Product>) repository.findAllByCategory(CategoryId);
    }

    public List<Product> Search(String search) {
        return repository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(search);
    }
    public List<ProductVM> newProduct() {
        List<ProductVM> vmlist = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByAddDateDesc();
        top6.forEach(x -> vmlist.add(new ProductVM(x)));
        return vmlist;
    }
    public List<ProductVM> popularProduct() {
        List<ProductVM> vmlist = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByVisitCountDesc();
        top6.forEach(x -> vmlist.add(new ProductVM(x)));
        return vmlist;
    }

    public List<ProductVM> ExpensiveProduct() {
        List<ProductVM> vmlist = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByPriceDesc();
        top6.forEach(x -> vmlist.add(new ProductVM(x)));
        return vmlist;
    }
    public List<ProductVM> CheapestProduct() {
        List<ProductVM> vmlist = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByPrice();
        top6.forEach(x->vmlist.add(new ProductVM(x)));
        return vmlist;
    }

    public Product getById(long id) {
        Optional<Product> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Product> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAll(page);
        return all.toList();
    }

    public List<Product> getAllByCategoryid(Long categoryId, Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAllByCategory(categoryId, page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public long getAllCountByCategoryId(Long categoryId) {
        return repository.countByCategory(categoryId);
    }

    public Product add(ProductVM vm) {
        Product data = vm.convert();
        if (vm.getFeatures() != null)
            vm.getFeatures().forEach(x -> data.addFeatures(featuresService.getById(x)));
        if (vm.getColors() != null)
            vm.getColors().forEach(x -> data.addColor(colorService.getById(x)));
        if (vm.getSizes() != null)
            vm.getSizes().forEach(x -> data.addSize(sizeService.getById(x)));

        data.setAddDate(new Date());
        ProductCategory productCategory = productCategoryService.getById(vm.getCategory());
        data.setCategory(productCategory);
        return repository.save(data);
    }

    public Product update(Product data) throws DataNotFoundException {
        Product oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription().substring(0,4000));
        oldData.setImage(data.getImage());
        oldData.setEnable(data.getEnable());
        oldData.setPrice(data.getPrice());
        oldData.setColors(data.getColors());
        oldData.setSizes(data.getSizes());
        oldData.setFeatures(data.getFeatures());
        oldData.setAddDate(new Date());
        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        List<Long> featuersListId = new ArrayList<>();
        oldData.getFeatures().forEach(x->featuersListId.add(x.getId()));
        repository.deleteById(id);
        featuersListId.forEach(x->{
            try {
                featuresService.delete(x);
            } catch (DataNotFoundException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    public Product increaseVisitCount(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        oldData.setVisitCount(oldData.getVisitCount() + 1);
        return repository.save(oldData);
    }

    public  Product deleteFeature(long productId ,long featureId) throws DataNotFoundException {
        Product oldData = getById(productId);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + productId + " not found");
        Product p = getById(productId);
        List<Features> features = p.getFeatures();
        if(features.size() == 0 || features == null)
            return p;
        features.removeIf(f-> f.getId() == featureId);
        p.setFeatures(features);
        repository.save(p);
        return p;
    }

}

