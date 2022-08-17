package holosen.shop.app.services.order;

import holosen.shop.app.entities.order.Invoice;
import holosen.shop.app.entities.product.Product;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.repositores.order.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> findALlByCustomer(long cid)
    {
        return repository.findAllByCustomer(cid);
    }
    public List<Invoice> findALlByCustomer(long cid,Integer pageSize ,Integer pageNumber)
    {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Invoice> all = repository.findAllByCustomerId(cid, page);
        return all.toList();
    }
    public long findALlCountByCustomer(Long cid) {
        return repository.countByCustomerId(cid);
    }

    public Invoice getById(long id) {
        Optional<Invoice> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Invoice add(Invoice data) {
        return repository.save(data);
    }

    public Invoice update(Invoice data) throws DataNotFoundException {
        Invoice oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setPayedDate(data.getPayedDate());

        return repository.save(oldData);

    }

    public boolean delete(long id) throws DataNotFoundException {
        Invoice oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+ id +" not found");
        repository.deleteById(id);
        return true;
    }

}

