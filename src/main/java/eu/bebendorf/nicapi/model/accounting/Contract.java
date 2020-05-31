package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;

import java.util.Date;

public class Contract implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String title;
    @SerializedName("descr")
    public String description;
    @SerializedName("next_accounting")
    public Date nextAccounting;
    @SerializedName("next_renewal")
    public Date nextRenewal;
    public String runtime;
    @SerializedName("notice_period")
    public String noticePeriod;
    public String prepayment;
    private int active;
    public double amount;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public boolean isActive(){
        return active > 0;
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public ContractPosition[] getPositions() throws NicAPIException {
        return nicAPI.accounting().getContractPositions(id);
    }
    public BillingPosition[] getBillingPositions() throws NicAPIException {
        return nicAPI.accounting().getContractBillingPositions(id);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
