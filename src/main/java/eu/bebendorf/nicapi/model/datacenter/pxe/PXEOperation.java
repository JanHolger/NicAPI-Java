package eu.bebendorf.nicapi.model.datacenter.pxe;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class PXEOperation implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    @SerializedName("mac_address")
    public String macAddress;
    public String action;
    public String commands;
    @SerializedName("is_raid")
    public int isRaid;
    public String password;
    public State state;
    @SerializedName("state_percent")
    public int statePercent;
    @SerializedName("state_text")
    public String stateText;
    @SerializedName("network_name")
    public String networkName;
    public String address;
    public Date start;
    public Date end;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void cancel() throws NicAPIException {
        nicAPI.datacenter().pxe().cancelOperation(id);
    }
    public enum State {
        DONE,
        EXPIRED,
        CANCELLED,
    }
}
