package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.account.User;

import java.util.Date;

public class Billing {
    public int id;
    public String user;
    public String number;
    public double netto;
    public String country;
    public double brutto;
    public Date date;
    @SerializedName("due_date")
    public Date dueDate;
    @SerializedName("state")
    public BillingState state;
    @SerializedName("payment_method")
    public String paymentMethod;
    @SerializedName("paid_at")
    public Date paidAt;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}
