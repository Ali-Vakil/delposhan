package holosen.shop.app.services.site;

import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.entities.site.Slider;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.site.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService {

    @Autowired
    private SliderRepository repository;

    public List<Slider> FindAllOrderByItemOrder() {
        return (List<Slider>) repository.findALlByEnableIsTrue(Sort.by("ItemOrder"));
    }

    public Slider getByItemOrder(int ItemOrder){
        return repository.findByItemOrder(ItemOrder);
    }

    public Slider getById(long id) {
        Optional<Slider> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public List<Slider> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("ItemOrder"));
        Page<Slider> all = repository.findAll(page);
        return all.toList();
    }
    public long getAllCount(){
        return repository.count();
    }

    public Slider add(Slider data) throws Exception {

        if (data.getTitle() == null || data.getTitle().equals(""))
            throw new Exception("Pleas Insert Title");
        if (data.getLink() == null || data.getLink().equals(""))
            throw new Exception("Pleas Insert The Link");
        if (data.getDescription() == null || data.getDescription().equals(""))
            throw new Exception("Pleas Insert Description");
        if (data.getImage() == null || data.getImage().equals(""))
            throw new Exception("Pleas Insert Image");

        Slider last = repository.findTopByOrderByItemOrderDesc();
        if (last != null && last.getItemOrder() > 0) {
            int itm = last.getItemOrder();
            data.setItemOrder(itm + 1);
        }
        else
            data.setItemOrder(1);

        return repository.save(data);
    }

    public Slider update(Slider data) throws DataNotFoundException {
        Slider oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.getEnable());
        oldData.setImage(data.getImage());
        oldData.setItemOrder(data.getItemOrder());
        oldData.setLink(data.getLink());
        return repository.save(oldData);

    }
    public boolean delete(long id) throws DataNotFoundException {
        Slider oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

    public Slider changeOrder(long id, int navigationId) throws Exception
    {
        Slider chooseData = getById(id);
        if (chooseData == null)
            throw new Exception("Data Not Found");
        if (navigationId == -1)
        {
            if (chooseData.getItemOrder() <= 1)
                return chooseData;
            int ItemOrderId = chooseData.getItemOrder() - 1;
            Slider dataInTargetOrderItem = getByItemOrder(ItemOrderId);
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
            Slider dataInTargetOrderItem = getByItemOrder(ItemOrderId);
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
}

