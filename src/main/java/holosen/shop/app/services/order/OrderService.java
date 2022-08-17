package holosen.shop.app.services.order;

import holosen.shop.app.entities.order.Order;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.helper.uiModels.people.OrderItemVM;
import holosen.shop.app.repositores.order.OrderRepository;
import holosen.shop.app.repositores.product.ProductRepository;
import holosen.shop.app.services.people.CustomerService;
import holosen.shop.app.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;


    public Order getById(long id) {
        Optional<Order> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Order add(Order data) {
        return repository.save(data);
    }

    public Order update(Order data) throws DataNotFoundException {
        Order oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setCount(data.getCount());
        oldData.setPrice(data.getPrice());
        return repository.save(oldData);

    }
    public boolean delete(long id) throws DataNotFoundException {
        Order oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

