package holosen.shop.app.services.order;

import holosen.shop.app.Zarinpal_Payment.Conteroller.ZarinpalService;
import holosen.shop.app.Zarinpal_Payment.model.VerifyRequest;
import holosen.shop.app.entities.order.Invoice;
import holosen.shop.app.entities.order.Order;
import holosen.shop.app.entities.order.Transactions;
import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.entities.product.Product;
import holosen.shop.app.enums.PaymentType;
import holosen.shop.app.helper.uiModels.people.PaymentVM;
import holosen.shop.app.helper.uiModels.people.startPaymentVM;
import holosen.shop.app.services.people.CustomerService;
import holosen.shop.app.services.people.UserService;
import holosen.shop.app.services.product.ColorService;
import holosen.shop.app.services.product.ProductService;
import holosen.shop.app.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PaymentService {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ZarinpalService zarinpalService;

    @Autowired
    private TransactionsService transactionsService;


    public startPaymentVM add(PaymentVM data) throws Exception {
        //insert user
        //insert customer
        //insert Order
        //insert invoice
        startPaymentVM respons = new startPaymentVM();

        User userInfo = userService.getByUsername(data.getCustomerVM().getUsername());
        if (userInfo == null) {
            userInfo = userService.add(data.getCustomerVM().getUserInfo());
        } else {
            if (!data.isUserLogin()) {
                userInfo = userService.auth(data.getCustomerVM().getUsername(), data.getCustomerVM().getPassword());
                if (userInfo == null)
                    throw new Exception("User name is existed please Use different User name");
            }
        }

        Customer customer = customerService.getByUserId(userInfo.getId());

        if (customer == null) {

            data.getCustomerVM().getCustomerInfo().setUser(userInfo);
            data.getCustomerVM().setUserid(userInfo.getId());
            customer = customerService.add(data.getCustomerVM().getCustomerInfo());
        }


        List<Order> orderItemList = new ArrayList<>();

        Customer finalCustomer = customer;
        data.getOrderItemVMList().forEach(x -> {
            Order orderItem = new Order();
            orderItem.setColor(x.getColorId());
            orderItem.setCount(x.getCount());
            orderItem.setCustomer(finalCustomer);
            Product product = productService.getById(x.getProductId());
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setSize(x.getSizeId());
            orderService.add(orderItem);
            orderItemList.add(orderItem);
            long totalPrice = orderItem.getPrice() * orderItem.getCount();
            respons.setAmount(respons.getAmount() + totalPrice);
        });

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(new Date());
        invoice.setOrderItems(orderItemList);
        invoice.setCustomer(customer);
        invoice.setPayedDate(null);
        invoiceService.add(invoice);

        respons.setDescription(orderItemList.size() + " product for : " + customer.getFirstName() + " " + customer.getLastName());
        respons.setMobile(customer.getMobile());
        respons.setEmail(customer.getEmail());
        respons.setCustomer(customer);
        respons.setInvoice(invoice);
        respons.setPaymentType(data.getPaymentType());
        return respons;
    }

    public String goToPayment(startPaymentVM startPaymentVM) throws Exception {

        String result = "#";
        if (startPaymentVM.getPaymentType() == PaymentType.zarinpal) {
            result = zarinpalService.gotoPayment(startPaymentVM);
        }
        transactionsService.add(startPaymentVM);
        return result;
    }

    public Transactions doVerify(Transactions transactions) throws Exception {

        Transactions result = null;
        if (transactions.getPaymentType() == PaymentType.zarinpal) {
            result = zarinpalService.gotoVerify(transactions);
            transactionsService.update(result);
            if (result.getTransactionStatus() == 100) {
                Invoice invoice = invoiceService.getById(result.getInvoice().getId());
                invoice.setPayedDate(new Date());
                invoiceService.update(invoice);
            }
        }
        return result;
    }


}
