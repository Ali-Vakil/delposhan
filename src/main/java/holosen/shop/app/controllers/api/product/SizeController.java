package holosen.shop.app.controllers.api.product;

import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.Size;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/size")
public class SizeController {

    @Autowired
    private SizeService service;

    @GetMapping("/")
    public ServiceRespons<Size> getAll() {
        try {
            List<Size> result = service.getAll();
            return new ServiceRespons<Size>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Size>(e);
        }
    }


    @GetMapping("/{id}")
    public ServiceRespons<Size> search(@PathVariable long id) {
        try {
            Size result = service.getById(id);
            return new ServiceRespons<Size>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Size>(e);
        }
    }
    @GetMapping("/getAll")
    public ServiceRespons<Size> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Size> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Size>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Size>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Size> add(@RequestBody Size data) {
        try {
            Size result = service.add(data);
             return new ServiceRespons<Size>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Size>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Size> update(@RequestBody Size data) {
        try {
            Size result = service.update(data);
            return new ServiceRespons<Size>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Size>(e);
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
