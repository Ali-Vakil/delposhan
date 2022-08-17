package holosen.shop.app.controllers.api.order;

import holosen.shop.app.config.JwtTokenUtil;
import holosen.shop.app.entities.order.Invoice;
import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.product.Size;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.enums.UserRole;
import holosen.shop.app.helper.exceptions.jwtTokenException;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.UserVM;
import holosen.shop.app.services.order.InvoiceService;
import holosen.shop.app.services.people.CustomerService;
import holosen.shop.app.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/Invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/find")
    public ServiceRespons<Invoice> find(@RequestParam long cid) {
        try {
            List<Invoice> result = service.findALlByCustomer(cid);
            return new ServiceRespons<Invoice>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Invoice>(e);
        }
    }
    @GetMapping("/findPage")
    public ServiceRespons<Invoice> findPage(@RequestParam long cid,
                                            @RequestParam Integer pageSize,
                                            @RequestParam Integer pageNumber,
                                            HttpServletRequest request) {
        try {

            UserVM userVM =  GetUserVmFromToken(request);

            if (userVM.getRole() != UserRole.Admin) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != cid)
                    throw new Exception("You can see only your invoices");
            }

            List<Invoice> result = service.findALlByCustomer(cid,pageSize, pageNumber);
            long totalCount = service.findALlCountByCustomer(cid);
            return new ServiceRespons<Invoice>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Invoice>(e);
        }
    }



    @GetMapping("/{id}")
    public ServiceRespons<Invoice> search(@PathVariable long id) {
        try {
            Invoice result = service.getById(id);
            return new ServiceRespons<Invoice>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Invoice>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Invoice> add(@RequestBody Invoice data) {
        try {
            Invoice result = service.add(data);
            return new ServiceRespons<Invoice>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Invoice>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Invoice> update(@RequestBody Invoice data) {
        try {
            Invoice result = service.update(data);
            return new ServiceRespons<Invoice>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Invoice>(e);
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



    private UserVM GetUserVmFromToken(HttpServletRequest request) throws jwtTokenException {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
            throw new jwtTokenException("Request Token Header dose not set ! ");
        String token = requestTokenHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (username == null)
            throw new jwtTokenException("userName not found");

        return  new UserVM(userService.getByUsername(username));
    }

}
