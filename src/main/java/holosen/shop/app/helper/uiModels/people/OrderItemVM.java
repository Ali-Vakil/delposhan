package holosen.shop.app.helper.uiModels.people;

import holosen.shop.app.entities.order.Order;
import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.product.Color;
import holosen.shop.app.entities.product.Product;
import holosen.shop.app.entities.product.Size;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class OrderItemVM {

    private long id;
    private long productId;
    private long customerId;
    private long count;
    private long price;
    private Color colorId;
    private Size sizeId;

    public OrderItemVM() {

    }
    public OrderItemVM(Order item ) {
        setId(item.getId());
        setCustomerId(item.getCustomer().getId());
        setProductId(item.getProduct().getId());
        setPrice(item.getPrice());
        setCount(item.getCount());
        setColorId(item.getColor());
        setSizeId(item.getSize());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public Color getColorId() {
        return colorId;
    }

    public void setColorId(Color colorId) {
        this.colorId = colorId;
    }

    public Size getSizeId() {
        return sizeId;
    }

    public void setSizeId(Size sizeId) {
        this.sizeId = sizeId;
    }
}
