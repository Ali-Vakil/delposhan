package holosen.shop.app.repositores.product;

import holosen.shop.app.entities.product.ProductCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory,Long>{
    List<ProductCategory> findAllByEnableIsTrue(Sort sort);
}
