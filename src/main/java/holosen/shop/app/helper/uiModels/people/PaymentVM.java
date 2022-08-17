package holosen.shop.app.helper.uiModels.people;

import holosen.shop.app.enums.PaymentType;

import java.util.List;

public class PaymentVM {
    private CustomerVM customerVM;
    private List<OrderItemVM> orderItemVMList;
    private PaymentType paymentType;
    private boolean userLogin;

    public CustomerVM getCustomerVM() {
        return customerVM;
    }

    public void setCustomerVM(CustomerVM customerVM) {
        this.customerVM = customerVM;
    }

    public List<OrderItemVM> getOrderItemVMList() {
        return orderItemVMList;
    }

    public void setOrderItemVMList(List<OrderItemVM> orderItemVMList) {
        this.orderItemVMList = orderItemVMList;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }
}
