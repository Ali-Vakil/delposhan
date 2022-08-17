package holosen.shop.app.entities.order;

import holosen.shop.app.entities.people.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "invoice_id")
    private List<Order> OrderItems;

    private Date invoiceDate;
    private Date payedDate;


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

    public List<Order> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(List<Order> orderItems) {
        OrderItems = orderItems;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public boolean isPayed() {
        return getPayedDate() != null;
    }

    public String getInvoiceDateStr() {
        Date date = invoiceDate;
        return date.toString().substring(0, 10);
    }
    public String getInvoiceDateStr2() {
        Date date = invoiceDate;
        return date.toString().substring(0, 19);
    }
    public String getPayedDateStr() {
        if (payedDate == null) return "";

        Date date = payedDate;
        return date.toString().substring(0, 10);

    }
    public String getPayedDateStr2() {
        if (payedDate == null) return "";
        Date date = payedDate;
        return date.toString().substring(0, 19);

    }

}
