package holosen.shop.app.entities.order;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.enums.PaymentType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    private Date addDate;
    private long amount;
    private String description;
    private String authority;
    private String refId;
    private long status;
    private String verifyStatus;
    private long transactionStatus;
    private String paymentType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public long getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(long transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public PaymentType getPaymentType() {
       PaymentType py = PaymentType.zarinpal;
       if(paymentType.equals("zarinpal"))
           py = PaymentType.zarinpal;
        return py;
    }

    public void setPaymentType(PaymentType paymentType) {
        if(paymentType == PaymentType.zarinpal)
        this.paymentType = "zarinpal";
    }
}
