package holosen.shop.app.repositores.site;

import holosen.shop.app.entities.site.Slider;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends PagingAndSortingRepository<Slider,Long>{

    List<Slider> findALlByEnableIsTrue(Sort sort);

    Slider findTopByOrderByItemOrderDesc();

    Slider findByItemOrder(int itemOrder);
}
