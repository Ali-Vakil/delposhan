package holosen.shop.app.repositores.site;

import holosen.shop.app.entities.site.Content;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content,Long> {
    Content findFirstByKey(String key);
    List<Content> findAll(Sort sort);
    List<Content> findAll();
}
