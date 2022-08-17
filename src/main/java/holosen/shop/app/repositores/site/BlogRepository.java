package holosen.shop.app.repositores.site;

import holosen.shop.app.entities.site.Blog;
import holosen.shop.app.enums.BlogStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    @Query("from Blog where title like concat('%',:search,'%') or description like concat('%',:search,'%')")
    List<Blog> findAllByTitleContainsAndDescriptionContains(String search);
    List<Blog> findAll(Sort sort);

    Page<Blog> findAllByStatusAndPublishDateLessThanEqual(BlogStatus status, Date date , Pageable pageable);
    Long countByStatusAndPublishDateLessThanEqual(BlogStatus status, Date date);
}
