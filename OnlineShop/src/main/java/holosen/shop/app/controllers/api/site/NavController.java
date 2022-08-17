package holosen.shop.app.controllers.api.site;

import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Nav")
public class NavController {

    @Autowired
    private NavService service;

    @GetMapping("/")
    public ServiceRespons<Nav> get() {
        try {
            List<Nav> result = service.FindAllOrderByItemOrder();
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceRespons<Nav> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Nav> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceRespons<Nav> search(@PathVariable long id) {
        try {
            Nav result = service.getById(id);
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Nav> add(@RequestBody Nav data) {
        try {
            Nav result = service.add(data);
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
        }
    }

    @PutMapping("/")
    public ServiceRespons<Nav> update(@RequestBody Nav data) {
        try {
            Nav result = service.update(data);
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
        }
    }

    @PostMapping("/updateOrder/{id}/{navigationId}")
    public ServiceRespons<Nav> updateOrder(@PathVariable long id, @PathVariable int navigationId) {
        try {
            Nav reaOrderNav = service.changeOrder(id, navigationId);
            return new ServiceRespons<Nav>(RespoonseStatus.SUCCESS, reaOrderNav);
        } catch (Exception e) {
            return new ServiceRespons<Nav>(e);
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
