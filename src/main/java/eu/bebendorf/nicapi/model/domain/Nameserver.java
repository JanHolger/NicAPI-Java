package eu.bebendorf.nicapi.model.domain;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;

public class Nameserver implements NicModel {
    private transient NicAPI nicAPI;
    int id;
    @SerializedName("servername")
    String serverName;
    String[] addresses;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("updated_at")
    String updatedAt;
    String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void refresh() throws NicAPIException {
        nicAPI.domain().refreshNameserver(serverName);
    }
    public void delete() throws NicAPIException {
        nicAPI.domain().deleteNameserver(serverName);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
