package holosen.shop.app.services.people;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.helper.utils.SecurityUtils;
import holosen.shop.app.repositores.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private SecurityUtils sq;

    public User auth(String user, String password) {
        //encrypt password
        String pass = sq.encryptSHA1(password);
        return repository.findUserByUsernameAndPassword(user, pass);
    }

    public User getByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public User add(User data) throws Exception {
        if (data.getLastName() == null || data.getLastName() == "")
            throw new Exception("Please fill LastName");
        if (data.getUsername() == null || data.getUsername() == "")
            throw new Exception("Please fill userName");
        if (data.getPassword() == null || data.getPassword() == "")
            throw new Exception("Please fill Password");
        if (data.getRole() == null)
            throw new Exception("Please fill role");

        User olduser = repository.findFirstByUsername(data.getUsername());
        if (olduser != null)
            return olduser;


        data.setPassword(sq.encryptSHA1(data.getPassword()));
        return repository.save(data);
    }

    public User update(User data) throws DataNotFoundException {
        User oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setEmail(data.getEmail());
        oldData.setEnable(data.isEnable());
        if (data.getPassword() != null || !data.getPassword().equals(""))
            oldData.setPassword(sq.encryptSHA1(data.getPassword()));

        return repository.save(oldData);

    }

    public User updateUsername(Customer data) throws DataNotFoundException {
        User oldData = getById(data.getUser().getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");
        oldData.setUsername(data.getUser().getUsername());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setEmail(data.getEmail());

        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        User oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        repository.deleteById(id);
        return true;
    }

    public User chengPassword(long id, String oldPassword, String newPassword) throws Exception {
        // encrypt password
        String oldPass = sq.encryptSHA1(oldPassword);
        String newPass = sq.encryptSHA1(newPassword);

        User user = getById(id);
        if (user == null)
            throw new DataNotFoundException("User not found");
        if (!user.getPassword().equals(oldPass))
            throw new Exception("User or password is incorrect");
        user.setPassword(newPass);
        return repository.save(user);
    }
}

