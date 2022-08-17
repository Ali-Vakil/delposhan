package holosen.shop.app.repositores.order;

import holosen.shop.app.entities.order.Invoice;
import holosen.shop.app.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

    @Query("from Invoice where customer.id = :customerId")
    List<Invoice> findAllByCustomer(long customerId);


    @Query( value =" from Invoice where customer.id = :cid",
            countQuery = "select count(id) from Invoice where customer.id = :cid")
    Page<Invoice> findAllByCustomerId(long cid, Pageable page);

    @Query("select count(id) from Invoice where customer.id = :cid")
    long countByCustomerId(long cid);

}
