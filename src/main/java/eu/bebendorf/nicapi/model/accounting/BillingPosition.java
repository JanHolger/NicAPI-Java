package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class BillingPosition implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String user;
    public String title;
    @SerializedName("descr")
    public String description;
    public int amount;
    private int groupable;
    public double price;
    @SerializedName("col_id")
    public int colId;
    public BillingState state;
    @SerializedName("billing")
    public String billingNumber;
    @SerializedName("billing_date")
    public Date billingDate;
    @SerializedName("available_at")
    public Date availableAt;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    @SerializedName("contract_position")
    public ContractPosition contractPosition;
    public boolean isGroupable(){
        return groupable > 0;
    }
    public Billing getBilling() throws NicAPIException {
        return nicAPI.accounting().getBilling(billingNumber);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
