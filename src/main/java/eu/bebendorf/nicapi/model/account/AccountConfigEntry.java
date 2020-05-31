package eu.bebendorf.nicapi.model.account;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AccountConfigEntry {
    public int id;
    public String key;
    public String value;
    public String user;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}
