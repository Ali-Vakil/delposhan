package holosen.shop.app.controllers.web;

import holosen.shop.app.config.JwtTokenUtil;
import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.enums.UserRole;
import holosen.shop.app.helper.uiModels.people.CustomerVM;
import holosen.shop.app.helper.uiModels.people.UserVM;
import holosen.shop.app.services.people.CustomerService;
import holosen.shop.app.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Stream;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String index(HttpServletRequest request, Model model){

        if(request.getCookies() == null)
            return "login";

        boolean haveUserToken = false;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("userToken")){
                haveUserToken = true;
            }
        }

        if(!haveUserToken)
            return "login";
        Cookie userTokenCookies = Arrays.stream(request.getCookies()).filter(x -> x.getName().toLowerCase().equals("userToken".toLowerCase())).findFirst().get();
        String userToken = userTokenCookies.getValue();
        String username = jwtTokenUtil.getUsernameFromToken(userToken);
        UserVM userVM = new UserVM(userService.getByUsername(username));

        model.addAttribute("user", userVM);
        if(userVM.getRole() == UserRole.Customer) {
            CustomerVM customer = new CustomerVM(customerService.getByUserId(userVM.getId()));
            model.addAttribute("customer", customer);
        }

        return "panel";
    }
}
