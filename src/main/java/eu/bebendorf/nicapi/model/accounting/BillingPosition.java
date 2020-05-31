package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BillingPosition {
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
}
