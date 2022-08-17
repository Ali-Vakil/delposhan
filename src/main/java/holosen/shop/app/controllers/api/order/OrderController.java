package holosen.shop.app.controllers.api.order;

import holosen.shop.app.entities.order.Order;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public ServiceRespons<Order> search(@PathVariable long id) {
        try {
            Order result = service.getById(id);
            return new ServiceRespons<Order>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Order>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Order> add(@RequestBody Order data) {
        try {
            Order result = service.add(data);
            return new ServiceRespons<Order>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Order>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Order> update(@RequestBody Order data) {
        try {
            Order result = service.update(data);
            return new ServiceRespons<Order>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Order>(e);
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
