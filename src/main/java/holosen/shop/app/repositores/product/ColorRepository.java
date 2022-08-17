package holosen.shop.app.repositores.product;

import holosen.shop.app.entities.product.Color;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends PagingAndSortingRepository<Color,Long> {
    @Override
    List<Color> findAll();
}
