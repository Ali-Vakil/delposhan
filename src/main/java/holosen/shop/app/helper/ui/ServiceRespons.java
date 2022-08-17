package holosen.shop.app.helper.ui;

import holosen.shop.app.enums.RespoonseStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceRespons<T> implements Serializable {
    private List<T> datalist;
    private RespoonseStatus status;
    private boolean haseError;
    private String message;
    private long totalCount;

    public ServiceRespons(RespoonseStatus status ,List<T> datalist) {
        this.datalist = datalist;
        this.status = status;
        this.haseError = status == RespoonseStatus.FAILED;
        this.message = "";
        this.totalCount = 0;
    }
    public ServiceRespons(RespoonseStatus status ,List<T> datalist,long TotalCount) {
        this.datalist = datalist;
        this.status = status;
        this.haseError = status == RespoonseStatus.FAILED;
        this.message = "";
        this.totalCount = TotalCount;
    }
    public ServiceRespons(RespoonseStatus status ,T data) {
        this.datalist = new ArrayList<T>();
        this.datalist.add(data);
        this.status = status;
        this.haseError = status == RespoonseStatus.FAILED;
        this.message = "";
        this.totalCount = 1;
    }
    public ServiceRespons(RespoonseStatus status ,String Message) {
        this.datalist = new ArrayList<T>();
        this.status = status;
        this.haseError =  status == RespoonseStatus.FAILED;
        this.message = Message;
        this.totalCount = 0;
    }
    public ServiceRespons(Exception ex) {
        this.datalist = new ArrayList<T>();
        this.status = RespoonseStatus.EXCEPTIONS;
        this.haseError = true;
        this.message = ex.getMessage();
        this.totalCount = 0;
    }



    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }

    public RespoonseStatus getStatus() {
        return status;
    }

    public void setStatus(RespoonseStatus status) {
        this.status = status;
    }

    public boolean isHaseError() {
        return haseError;
    }

    public void setHaseError(boolean haseError) {
        this.haseError = haseError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
