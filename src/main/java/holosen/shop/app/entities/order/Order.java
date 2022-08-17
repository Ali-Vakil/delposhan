package holosen.shop.app.entities.order;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.Product;
import holosen.shop.app.entities.product.Size;

import javax.persistence.*;

@Entity
@Table(name = "OtrderItem")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "costomer_id")
    private Customer customer;
    private long count;
    private long price;
    @OneToOne
    @JoinColumn(name="color_id")
    private Color color;
    @OneToOne
    @JoinColumn(name="size_id")
    private Size size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
