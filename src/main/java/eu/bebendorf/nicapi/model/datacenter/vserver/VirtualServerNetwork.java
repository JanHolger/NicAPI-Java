package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.annotations.SerializedName;

public class VirtualServerNetwork {
    public int id;
    @SerializedName("netname")
    public String netName;
    @SerializedName("mac_address")
    public String macAddress;
    @SerializedName("vserver_id")
    public int virtualServerId;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    public String user;
}
