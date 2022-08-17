package holosen.shop.app.repositores.product;

import holosen.shop.app.entities.product.Product;
import org.springframework.boot.jta.atomikos.AtomikosProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query("from Product where category.id =:categoryId")
    List<Product> findAllByCategory(long categoryId);

    @Query("select count(id) from Product where category.id =:categoryId")
    long countByCategory(long categoryId);


    @Query( value =" from Product where category.id =:categoryId",
    countQuery = "select count(id) from Product where category.id =:categoryId")
    Page<Product> findAllByCategory(long categoryId,Pageable page);

    @Query("from Product where enable = true and ( title like concat('%',:search,'%') or description like concat('%',:search,'%'))")
    List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);

    List<Product> findTop6ByOrderByAddDateDesc();

    List<Product> findTop6ByOrderByVisitCountDesc();

    List<Product> findTop6ByOrderByPrice();

    List<Product> findTop6ByOrderByPriceDesc();

}
