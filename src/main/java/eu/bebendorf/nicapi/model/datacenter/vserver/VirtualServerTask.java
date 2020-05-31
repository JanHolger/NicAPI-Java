package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class VirtualServerTask {
    public int id;
    @SerializedName("v_server_id")
    public int virtualServerId;
    public String type;
    @SerializedName("start_time")
    public Date startTime;
    @SerializedName("end_time")
    public Date endTime;
    public String state;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}
