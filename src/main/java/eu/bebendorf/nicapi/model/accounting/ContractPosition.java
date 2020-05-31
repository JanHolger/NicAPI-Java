package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class ContractPosition implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    @SerializedName("contract")
    public int contractId;
    public String title;
    @SerializedName("descr")
    public String description;
    public int amount;
    public double price;
    public double sum;
    private int cancellable;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public boolean isCancellable(){
        return cancellable > 0;
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public Contract getContract() throws NicAPIException {
        return nicAPI.accounting().getContract(contractId);
    }
}
