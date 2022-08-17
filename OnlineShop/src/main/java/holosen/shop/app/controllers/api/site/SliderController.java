package holosen.shop.app.controllers.api.site;

import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.entities.site.Slider;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.site.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
public class SliderController {

    @Autowired
    private SliderService service;

    @GetMapping("/")
    public ServiceRespons<Slider> get() {
        try {
            List<Slider> result = service.FindAllOrderByItemOrder();
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceRespons<Slider> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Slider> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceRespons<Slider> search(@PathVariable long id) {
        try {
            Slider result = service.getById(id);
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Slider> add(@RequestBody Slider data) {
        try {

            Slider result = service.add(data);
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Slider> update(@RequestBody Slider data) {
        try {
            Slider result = service.update(data);
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
        }
    }
    @PostMapping("/updateOrder/{id}/{navigationId}")
    public ServiceRespons<Slider> updateOrder(@PathVariable long id, @PathVariable int navigationId) {
        try {
            Slider reaOrder = service.changeOrder(id, navigationId);
            return new ServiceRespons<Slider>(RespoonseStatus.SUCCESS, reaOrder);
        } catch (Exception e) {
            return new ServiceRespons<Slider>(e);
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
