package holosen.shop.app.repositores.order;

import holosen.shop.app.entities.order.Order;
import holosen.shop.app.entities.order.Transactions;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends PagingAndSortingRepository<Transactions,Long> {

    Transactions findByAuthority(String authority);
}
