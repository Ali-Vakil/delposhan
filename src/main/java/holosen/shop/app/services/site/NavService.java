package holosen.shop.app.services.site;

import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.repositores.site.NavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavService {

    @Autowired
    private NavRepository repository;

    public List<Nav> FindAllOrderByItemOrder() {
        return (List<Nav>) repository.findAllByEnableIsTrue(Sort.by("ItemOrder"));
    }

    public List<Nav> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("ItemOrder"));
        Page<Nav> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public Nav getById(long id) {
        Optional<Nav> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Nav getByItemOrder(int itemOrder) {
        Nav data = repository.findByItemOrder(itemOrder);
        return data;
    }

    public Nav changeOrder(long id, int navigationId) throws Exception
    {
        Nav chooseData = getById(id);
        if (chooseData == null)
            throw new Exception("Data Not Found");
        if (navigationId == -1)
        {
            if (chooseData.getItemOrder() <= 1)
                return chooseData;
            int ItemOrderId = chooseData.getItemOrder() - 1;
            Nav dataInTargetOrderItem = getByItemOrder(ItemOrderId);
            if (dataInTargetOrderItem == null)
            {
                chooseData.setItemOrder(chooseData.getItemOrder() - 1);
                repository.save(chooseData);
                return chooseData;
            }

            chooseData.setItemOrder(dataInTargetOrderItem.getItemOrder());
            dataInTargetOrderItem.setItemOrder(dataInTargetOrderItem.getItemOrder() + 1);
            repository.save(dataInTargetOrderItem);
            repository.save(chooseData);
            return chooseData;

        }
        else
        {
            int ItemOrderId = chooseData.getItemOrder() + 1;
            Nav dataInTargetOrderItem = getByItemOrder(ItemOrderId);
            if (dataInTargetOrderItem == null) {
                chooseData.setItemOrder(chooseData.getItemOrder() + 1);
                repository.save(chooseData);
                return chooseData;
            }
            chooseData.setItemOrder(dataInTargetOrderItem.getItemOrder());
            dataInTargetOrderItem.setItemOrder(dataInTargetOrderItem.getItemOrder() - 1);
            repository.save(dataInTargetOrderItem);
            repository.save(chooseData);
            return chooseData;
        }
    }

    public Nav add(Nav data) throws Exception {
        if (data.getTitle() == null || data.getTitle().equals(""))
            throw new Exception("Pleas Insert Title");
        if (data.getLink() == null || data.getLink().equals(""))
            throw new Exception("Pleas Insert The Link");

        Nav last = repository.findTopByOrderByItemOrderDesc();
        if (last != null && last.getItemOrder() > 0)
            data.setItemOrder(last.getItemOrder() + 1);
        else
            data.setItemOrder(1);

        return repository.save(data);
    }

    public Nav update(Nav data) throws DataNotFoundException {
        Nav oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + data.getId() + " not found");
        oldData.setTitle(data.getTitle());
        oldData.setEnable(data.getEnable());
        oldData.setItemOrder(data.getItemOrder());
        oldData.setLink(data.getLink());
        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        Nav oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : " + id + " not found");
        repository.deleteById(id);
        return true;
    }

}

