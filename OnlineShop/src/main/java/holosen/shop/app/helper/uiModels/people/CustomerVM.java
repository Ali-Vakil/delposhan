package holosen.shop.app.helper.uiModels.people;

import holosen.shop.app.entities.people.Customer;
import holosen.shop.app.entities.people.User;
import holosen.shop.app.enums.UserRole;

public class CustomerVM {
    private long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String tel;
    private String address;
    private String postalCode;
    private String email;

    private long Userid;
    private String username;
    private String password;

    public CustomerVM() {
    }
    public CustomerVM(Customer customer) {
        setId(customer.getId());
        setFirstName(customer.getFirstName());
        setLastName(customer.getLastName());
        setMobile(customer.getMobile());
        setTel(customer.getTel());
        setAddress(customer.getAddress());
        setPostalCode(customer.getPostalCode());
        setEmail(customer.getEmail());
        setUserid(customer.getUser().getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getUserid() {
        return Userid;
    }

    public void setUserid(long userid) {
        Userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomerInfo(){
        Customer customer = new Customer();
        customer.setId(getId());
        customer.setTel(getTel());
        customer.setAddress(getAddress());
        customer.setPostalCode(getPostalCode());
        customer.setEmail(getEmail());
        customer.setFirstName(getFirstName());
        customer.setLastName(getLastName());
        customer.setMobile(getMobile());
        customer.setUser(getUserInfo());
        return  customer;
    }

    public User getUserInfo(){
        User user = new User();
        user.setId(getUserid());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setEmail(getEmail());
        user.setRole(UserRole.Customer);
        user.setEnable(true);
        return user;
    }
}
