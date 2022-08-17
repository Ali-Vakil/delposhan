package holosen.shop.app.controllers.api.product;

import holosen.shop.app.entities.product.ProductCategory;
import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.product.ProductCategoryService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/productcategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping("")
    public ServiceRespons<ProductCategory> get() {
        try {
            List<ProductCategory> result = service.findAll();
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
        }
    }


    @GetMapping("/getAll")
    public ServiceRespons<ProductCategory> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<ProductCategory> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceRespons<ProductCategory> search(@PathVariable long id) {
        try {
            ProductCategory result = service.getById(id);
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
        }
    }
    @GetMapping("/info/{id}")
    public ServiceRespons<ProductCategory> searchInfo(@PathVariable long id) {
        try {
            ProductCategory result = service.getById(id);
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<ProductCategory> add(@RequestBody ProductCategory data) {
        try {
            ProductCategory result = service.add(data);
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<ProductCategory> update(@RequestBody ProductCategory data) {
        try {
            ProductCategory result = service.update(data);
            return new ServiceRespons<ProductCategory>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<ProductCategory>(e);
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

}
