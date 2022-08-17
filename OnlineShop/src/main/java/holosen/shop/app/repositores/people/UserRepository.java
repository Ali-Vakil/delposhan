package holosen.shop.app.repositores.people;

import holosen.shop.app.entities.people.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findUserByUsernameAndPassword(String username,String password);
    User findFirstByUsername(String username);
}
