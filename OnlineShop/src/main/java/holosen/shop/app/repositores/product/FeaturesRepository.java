package holosen.shop.app.repositores.product;

import holosen.shop.app.entities.product.Features;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends PagingAndSortingRepository<Features,Long> {
}
