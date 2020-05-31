package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Transaction {
    public int id;
    public String title;
    @SerializedName("descr")
    public String description;
    public JsonElement notes;
    public double amount;
    public double balance;
    public Type type;
    public Date date;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public enum Type {
        TRANSFER,
        INTERNAL_TRANSACTION
    }
}
