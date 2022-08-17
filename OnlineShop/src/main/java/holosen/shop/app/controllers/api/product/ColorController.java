package holosen.shop.app.controllers.api.product;

import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.ProductCategory;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.product.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/color")
public class ColorController {

    @Autowired
    private ColorService service;

    @GetMapping("/")
    public ServiceRespons<Color> getAll() {
        try {
            List<Color> result = service.getAll();
            return new ServiceRespons<Color>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Color>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceRespons<Color> search(@PathVariable long id) {
        try {
            Color result = service.getById(id);
            return new ServiceRespons<Color>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Color>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceRespons<Color> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Color> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Color>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Color>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Color> add(@RequestBody Color data) {
        try {
            Color result = service.add(data);
            return new ServiceRespons<Color>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Color>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Color> update(@RequestBody Color data) {
        try {
            Color result = service.update(data);
            return new ServiceRespons<Color>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Color>(e);
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
