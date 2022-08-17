package holosen.shop.app.controllers.web;

import holosen.shop.app.entities.order.Transactions;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.services.order.PaymentService;
import holosen.shop.app.services.order.TransactionsService;
import holosen.shop.app.services.product.ProductService;
import holosen.shop.app.services.site.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/blog/info/{id}")
    public String bloginfo(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            blogService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "blogInfo";
    }

    @GetMapping("/product/getAll/{id}")
    public String productCategory(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        return "productCategory";
    }

    @GetMapping("/oneProduct/{id}")
    public String oneProductCategory(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            productService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "oneProduct";
    }

    @GetMapping("/basket")
    public String basket() {
        return "basket";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String Authority,
                         @RequestParam String Status,
                         Model model) throws DataNotFoundException {

        try {
            Transactions transactions = transactionsService.getByAuthority(Authority);
            if (transactions == null)
                throw new Exception("Data not found");

            transactions.setVerifyStatus(Status);


            if (((transactions.getRefId() == null || transactions.getRefId().equals(""))
                    &&(Status.toLowerCase().equals("OK".toLowerCase()))))
            {
                Transactions result = paymentService.doVerify(transactions);
                transactionsService.update(transactions);
                model.addAttribute("transaction", result);
            }
            else
            {
                model.addAttribute("transaction", transactions);
            }

            return "verify";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "home";
        }
    }
}
