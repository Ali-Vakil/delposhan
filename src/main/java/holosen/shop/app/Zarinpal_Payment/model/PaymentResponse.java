package holosen.shop.app.Zarinpal_Payment.model;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String Authority;
    private Long Status;

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public Long getStatus() {
        return Status;
    }

    public void setStatus(Long status) {
        Status = status;
    }
}
