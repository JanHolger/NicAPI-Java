package eu.bebendorf.nicapi.model.account;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class APIToken implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String token;
    public String user;
    @SerializedName("valid_until")
    public Date validUntil;
    @SerializedName("renew_interval")
    public String renewInterval;
    @SerializedName("last_activity")
    public Date lastActivity;
    @SerializedName("last_ip")
    public String lastIp;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void deactivate() throws NicAPIException {
        nicAPI.account().deactivateAPIToken(token);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
