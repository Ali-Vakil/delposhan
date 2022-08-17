package holosen.shop.app.repositores.product;

import holosen.shop.app.entities.product.Size;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends PagingAndSortingRepository<Size,Long> {

    @Override
    List<Size> findAll();
}
