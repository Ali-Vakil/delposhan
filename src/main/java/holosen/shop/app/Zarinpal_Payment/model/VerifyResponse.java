package holosen.shop.app.Zarinpal_Payment.model;

public class VerifyResponse {
  private long Status;
  private String RefID;

    public long getStatus() {
        return Status;
    }

    public void setStatus(long status) {
        Status = status;
    }

    public String getRefID() {
        return RefID;
    }

    public void setRefID(String RefId) {
        RefID = RefId;
    }
}
