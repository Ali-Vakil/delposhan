package holosen.shop.app.Zarinpal_Payment.model;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
    private String MerchantID;
    private Long Amount;
    private String CallbackURL;
    private String Description;
    private String Email;
    private String Mobile;

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getCallbackURL() {
        return CallbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        CallbackURL = callbackURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
