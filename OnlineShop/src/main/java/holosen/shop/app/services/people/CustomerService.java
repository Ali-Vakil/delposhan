package holosen.shop.app.services.people;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.people.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private UserService userService;

    public List<Customer> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Customer> all = repository.findAll(page);
        return all.toList();
    }
    public long getAllCount() {
        return repository.count();
    }

    public Customer getById(long id) {
        Optional<Customer> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public Customer getByUserId(long id) {
        Customer data = repository.findByUserId(id);
        return data;
    }

    public Customer add(Customer data) {
        return repository.save(data);
    }

    public Customer update(Customer data) throws DataNotFoundException {
        Customer oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setEmail(data.getEmail());
        oldData.setAddress(data.getAddress());
        oldData.setMobile(data.getMobile());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());
        return repository.save(oldData);

    }
    public Customer updateWithUser(Customer data) throws DataNotFoundException {

        Customer oldData = getById(data.getId());

        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");

        User user = userService.getById(oldData.getUser().getId());
        if(user.getId() == data.getUser().getId()) {
            userService.updateUsername(data);
        }


        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setEmail(data.getEmail());
        oldData.setAddress(data.getAddress());
        oldData.setMobile(data.getMobile());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());

        return repository.save(oldData);

    }
    public boolean delete(long id) throws DataNotFoundException {
        Customer oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

