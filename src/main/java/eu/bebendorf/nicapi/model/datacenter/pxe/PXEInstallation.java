package eu.bebendorf.nicapi.model.datacenter.pxe;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class PXEInstallation implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    @SerializedName("mac_address")
    public String macAddress;
    public String commands;
    public String hostname;
    public String template;
    @SerializedName("template_id")
    public int templateId;
    public String password;
    @SerializedName("ssh_key")
    public String sshKey;
    @SerializedName("support_ssh_key")
    public int supportSSHKey;
    @SerializedName("raid_level")
    public int raidLevel;
    @SerializedName("raid_disk_count")
    public int raidDiskCount;
    @SerializedName("additional_software")
    // NOT SURE ABOUT THE DATATYPE
    public JsonElement additionalSoftware;
    public State state;
    @SerializedName("state_percent")
    public int statePercent;
    public Date start;
    public Date end;
    @SerializedName("network_name")
    public String networkName;
    public String address;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void cancel() throws NicAPIException {
        nicAPI.datacenter().pxe().cancelInstallation(id);
    }
    public enum State {
        DONE,
        EXPIRED,
        CANCELLED,
    }
}
