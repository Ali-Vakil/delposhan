package holosen.shop.app.repositores.people;

import holosen.shop.app.entities.people.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    @Query("from Customer where user.id = :id")
    Customer findByUserId(long id);

}
