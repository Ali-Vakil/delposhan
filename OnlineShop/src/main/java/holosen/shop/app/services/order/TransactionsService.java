package holosen.shop.app.services.order;

import holosen.shop.app.entities.order.Transactions;
import holosen.shop.app.helper.exceptions.DataNotFoundException;
import holosen.shop.app.helper.uiModels.people.startPaymentVM;
import holosen.shop.app.repositores.order.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    public Transactions getByAuthority(String authority) {
        Transactions data = repository.findByAuthority(authority);
        return data;
    }

    public Transactions getById(long id) {
        Optional<Transactions> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Transactions add(Transactions data) {
        return repository.save(data);
    }
    public Transactions add(startPaymentVM startPaymentVM) {
        Transactions data = new Transactions();
        data.setRefId("");
        data.setAddDate(new Date());
        data.setAmount(startPaymentVM.getAmount());
        data.setCustomer(startPaymentVM.getCustomer());
        data.setInvoice(startPaymentVM.getInvoice());
        data.setDescription(startPaymentVM.getDescription());
        data.setAuthority(startPaymentVM.getAuthority());
        data.setStatus(startPaymentVM.getStatus());
        data.setPaymentType(startPaymentVM.getPaymentType());
        return repository.save(data);
    }
    public Transactions update(Transactions data) throws DataNotFoundException {
        Transactions oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id : "+data.getId()+" not found");
        oldData.setRefId(data.getRefId());
        oldData.setVerifyStatus(data.getVerifyStatus());
        oldData.setTransactionStatus(data.getTransactionStatus());

        return repository.save(oldData);

    }

}

