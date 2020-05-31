package eu.bebendorf.nicapi.model.misc;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TeamspeakInstance {
    public int id;
    public String title;
    @SerializedName("descr")
    public String description;
    public String address;
    public int port;
    @SerializedName("ts_user")
    public String username;
    @SerializedName("ts_password")
    public String password;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
}
