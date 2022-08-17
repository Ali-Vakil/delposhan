package holosen.shop.app.Zarinpal_Payment.Conteroller;

import holosen.shop.app.Zarinpal_Payment.HttpUtils;
import holosen.shop.app.Zarinpal_Payment.model.PaymentRequest;
import holosen.shop.app.Zarinpal_Payment.model.PaymentResponse;
import holosen.shop.app.Zarinpal_Payment.model.VerifyRequest;
import holosen.shop.app.Zarinpal_Payment.model.VerifyResponse;
import holosen.shop.app.entities.order.Transactions;
import holosen.shop.app.helper.uiModels.people.startPaymentVM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ZarinpalService {

    @Value("${zarinpal.MerchantID}")
    private String merchantid;

    @Value("${zarinpal.calBackUrl}")
    private String calBackUrl;

    @Value("${zarinpal.paymentAddress}")
    private String paymentAddress;

    @Value("${zarinpal.startPayAddress}")
    private String startPayAddress;

    @Value("${zarinpal.verifycationAddress}")
    private String verifycationAddress;

    public String gotoPayment(startPaymentVM data) throws Exception {

        PaymentRequest request = new PaymentRequest();
        request.setAmount(data.getAmount());
        request.setDescription(data.getDescription());
        request.setCallbackURL(calBackUrl);
        request.setMerchantID(merchantid);
        request.setMobile(data.getMobile());
        request.setEmail(data.getEmail());


        HttpUtils<PaymentResponse> httpUtils = new HttpUtils<>(PaymentResponse.class);
        PaymentResponse response = httpUtils.callPost(paymentAddress, request);

        data.setAuthority(response.getAuthority());
        data.setStatus(response.getStatus());
        if (response.getStatus() != 100) {
            throw new Exception("Error is Happened in request");
        }


        return startPayAddress + response.getAuthority();
    }

    public Transactions gotoVerify(Transactions transactions) throws Exception {

        VerifyRequest verifyRequest = new VerifyRequest();
        verifyRequest.setAmount(transactions.getAmount());
        verifyRequest.setAuthority(transactions.getAuthority());
        verifyRequest.setMerchantID(merchantid);

        HttpUtils<VerifyResponse> httpUtils = new HttpUtils<>(VerifyResponse.class);
        VerifyResponse response = httpUtils.callPost(verifycationAddress, verifyRequest);

        transactions.setTransactionStatus(response.getStatus());
        transactions.setRefId(response.getRefID());

        if (response.getStatus() != 100) {
            throw new Exception("Error is Happened in request");
        }

        return transactions;
    }
}
