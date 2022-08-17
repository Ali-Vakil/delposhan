package holosen.shop.app.controllers.api.people;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.CustomerVM;
import holosen.shop.app.helper.uiModels.people.UserVM;
import holosen.shop.app.services.people.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/Customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/getAll")
    public ServiceRespons<Customer> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Customer> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceRespons<Customer> search(@PathVariable long id) {
        try {
            Customer result = service.getById(id);
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
        }
    }

    @GetMapping("/getByUserId/{id}")
    public ServiceRespons<Customer> getByUserID(@PathVariable long id) {
        try {
            Customer result = service.getByUserId(id);
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Customer> add(@RequestBody Customer data) {
        try {
            Customer result = service.add(data);
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Customer> update(@RequestBody Customer data) {
        try {
            Customer result = service.update(data);
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
        }
    }
    @PutMapping("/withUser")
    public ServiceRespons<Customer> updateWithUsername(@RequestBody Customer data) {
        try {
            Customer result = service.updateWithUser(data);
            return new ServiceRespons<Customer>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Customer>(e);
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
