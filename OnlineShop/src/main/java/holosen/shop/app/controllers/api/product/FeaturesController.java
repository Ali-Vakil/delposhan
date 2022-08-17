package holosen.shop.app.controllers.api.product;

import holosen.shop.app.entities.product.Features;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.product.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/features")
public class FeaturesController {

    @Autowired
    private FeaturesService service;

    @GetMapping("/{id}")
    public ServiceRespons<Features> search(@PathVariable long id) {
        try {
            Features result = service.getById(id);
            return new ServiceRespons<Features>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Features>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Features> add(@RequestBody Features data) {
        try {
            Features result = service.add(data);
            return new ServiceRespons<Features>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Features>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Features> update(@RequestBody Features data) {
        try {
            Features result = service.update(data);
            return new ServiceRespons<Features>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Features>(e);
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
