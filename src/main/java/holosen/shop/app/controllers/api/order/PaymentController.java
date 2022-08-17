package holosen.shop.app.controllers.api.order;

import holosen.shop.app.entities.order.Invoice;
import holosen.shop.app.entities.order.Order;
import holosen.shop.app.enums.PaymentType;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.OrderItemVM;
import holosen.shop.app.helper.uiModels.people.PaymentVM;
import holosen.shop.app.helper.uiModels.people.startPaymentVM;
import holosen.shop.app.services.order.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/Payment")
public class PaymentController {

    @Autowired
    private PaymentService service;


    @PostMapping("/")
    public ServiceRespons<startPaymentVM> addPayment(@RequestBody PaymentVM data) {
        try {
            startPaymentVM result = service.add(data);
            String location = service.goToPayment(result);
            result.setLocation(location);
            return new ServiceRespons<startPaymentVM>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<startPaymentVM>(e);
        }
    }
    @PostMapping("/existInvoicePayment")
    public ServiceRespons<startPaymentVM> existInvoicePayment(@RequestBody Invoice invoice) {
        try {
            startPaymentVM result = getStartPaymentVMFromInvoice(invoice);
            String location = service.goToPayment(result);
            result.setLocation(location);
            return new ServiceRespons<startPaymentVM>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<startPaymentVM>(e);
        }
    }

    private startPaymentVM getStartPaymentVMFromInvoice(Invoice invoice){
        startPaymentVM result = new startPaymentVM();


        long myAmount = invoice.getOrderItems().stream().mapToLong(x -> x.getCount() * x.getPrice()).sum();
        long myCount = invoice.getOrderItems().stream().mapToLong(x -> x.getCount()).sum();


        result.setAmount(myAmount);
        result.setDescription( myCount + " product for "+ invoice.getCustomer().getFullName());

        result.setEmail(invoice.getCustomer().getEmail());
        result.setMobile(invoice.getCustomer().getMobile());
        result.setPaymentType(PaymentType.zarinpal);
        result.setInvoice(invoice);
        result.setCustomer(invoice.getCustomer());



        return  result;
    }

}
