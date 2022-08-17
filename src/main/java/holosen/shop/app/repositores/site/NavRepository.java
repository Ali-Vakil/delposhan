package holosen.shop.app.repositores.site;

import holosen.shop.app.entities.site.Nav;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavRepository extends PagingAndSortingRepository<Nav,Long> {
    List<Nav> findAllByEnableIsTrue(Sort sort);
    Nav findTopByOrderByItemOrderDesc();
    Nav findByItemOrder(int itemOrder);
    List<Nav> findAll(Sort sort);
}
