package holosen.shop.app.controllers.api.people;

import holosen.shop.app.config.JwtTokenUtil;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.exceptions.jwtTokenException;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.UserVM;
import holosen.shop.app.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    // for generate token we need this class
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("login")
    public ServiceRespons<UserVM> login(@RequestBody User user){

        User userData = service.auth(user.getUsername(), user.getPassword());
        if(userData == null)
            return new ServiceRespons<UserVM>(RespoonseStatus.FAILED,"Incorrect User OR Password");

        UserVM userVM = new UserVM(userData);
        //generate token
        String token = jwtTokenUtil.generateToken(userVM);
        userVM.setToken(token);

        return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS,userVM);
    }

    @GetMapping("/getAll")
    public ServiceRespons<UserVM> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<User> result = service.getAll(pageSize, pageNumber);
            List<UserVM> resultVM = new ArrayList<>();
            result.stream().forEach(x->resultVM.add(new UserVM(x)));
            long totalCount = service.getAllCount();
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS, resultVM, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceRespons<UserVM> search(@PathVariable long id) {
        try {
            User result = service.getById(id);
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS,new UserVM(result));
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
        }
    }
    @GetMapping("/getUserInfo")
    public ServiceRespons<UserVM> getUserInfo(HttpServletRequest request) {
        try {
            String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new jwtTokenException("request token header does not set");
            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new jwtTokenException("username not find");

            UserVM uservm = new UserVM(service.getByUsername(username));

//            if (user.getRole() != UserRole.Admin) {
//                Customer customer = CustomerService.getById(user.getId());
//                user.setCustomerId(customer.getId());
//                user.setCustomer(new CustomerVM(customer));
//            }
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS, uservm);
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<UserVM> add(@RequestBody User data) {
        try {
            User result = service.add(data);
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS,new UserVM(result));
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<UserVM> update(@RequestBody User data) {
        try {
            User result = service.update(data);
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
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
    @PutMapping("/chengPassword")
    public ServiceRespons<UserVM> chengPassword(@RequestBody UserVM data)
    {
        try {
            User result = service.chengPassword(data.getId(),data.getPassword(), data.getNewPassword());
            return new ServiceRespons<UserVM>(RespoonseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceRespons<UserVM>(e);
        }

    }
    
}
