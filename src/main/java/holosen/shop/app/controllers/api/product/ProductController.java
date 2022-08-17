package holosen.shop.app.controllers.api.product;

import holosen.shop.app.entities.product.Product;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.ProductVM;
import holosen.shop.app.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/category")
    public ServiceRespons<Product> findByCategory(@RequestParam long Cid) {
        try {
            List<Product> result = service.findAllbycategory(Cid);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }
    @GetMapping("/getAll")
    public ServiceRespons<Product> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Product> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }
    @GetMapping("/getAll/{Cid}")
    public ServiceRespons<Product> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber,@PathVariable long Cid) {
        try {
            List<Product> result = service.getAllByCategoryid(Cid,pageSize, pageNumber);
            long totalCount = service.getAllCountByCategoryId(Cid);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }



    @GetMapping("/find")
    public ServiceRespons<Product> search(@RequestParam String keyWord) {
        try {
            List<Product> result = service.Search(keyWord);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }

    @GetMapping("/newProduct")
    public ServiceRespons<ProductVM> newProduct() {
        try {
            List<ProductVM> result = service.newProduct();
            return new ServiceRespons<ProductVM>(RespoonseStatus.SUCCESS,result);
        } catch (Exception e) {
            return new ServiceRespons<ProductVM>(e);
        }
    }
    @GetMapping("/popularProduct")
    public ServiceRespons<ProductVM> popularProduct() {
        try {
            List<ProductVM> result = service.popularProduct();
            return new ServiceRespons<ProductVM>(RespoonseStatus.SUCCESS,result);
        } catch (Exception e) {
            return new ServiceRespons<ProductVM>(e);
        }
    }
    @GetMapping("/expensiveProduct")
    public ServiceRespons<ProductVM> expensiveProduct() {
        try {
            List<ProductVM> result = service.ExpensiveProduct();
            return new ServiceRespons<ProductVM>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductVM>(e);
        }
    }
    @GetMapping("/cheapestProduct")
    public ServiceRespons<ProductVM> cheapestProduct() {
        try {
            List<ProductVM> result = service.CheapestProduct();
            return new ServiceRespons<ProductVM>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductVM>(e);
        }
    }

    @GetMapping("/info/{id}")
    public ServiceRespons<ProductVM> getById(@PathVariable long id) {
        try {
            Product result = service.getById(id);
            return new ServiceRespons<ProductVM>(RespoonseStatus.SUCCESS, new ProductVM(result));
        } catch (Exception e) {
            return new ServiceRespons<ProductVM>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Product> add(@RequestBody ProductVM data) {
        try {
            Product result = service.add(data);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Product> update(@RequestBody Product data) {
        try {
            Product result = service.update(data);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceRespons<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.delete(id);
            return new ServiceRespons<Boolean>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Boolean>(e);
        }
    }
    @DeleteMapping("/feature/{productid}/{featureid}")
    public ServiceRespons<Product> deleteProductFeature(@PathVariable long productid,@PathVariable long featureid) {
        try {
            Product result = service.deleteFeature(productid,featureid);
            return new ServiceRespons<Product>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Product>(e);
        }
    }

}
