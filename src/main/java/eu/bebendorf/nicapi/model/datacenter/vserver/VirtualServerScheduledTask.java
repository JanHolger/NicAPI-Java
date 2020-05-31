package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;

import java.util.Date;

public class VirtualServerScheduledTask implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    @SerializedName("vserver_id")
    public int virtualServerId;
    @SerializedName("type")
    public String action;
    @SerializedName("last_execution")
    public Date lastExecution;
    @SerializedName("next_execution")
    public Date nextExecution;
    public String interval;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void delete() throws NicAPIException {
        nicAPI.datacenter().virtualServer().deleteScheduledTask(virtualServerId, id);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
