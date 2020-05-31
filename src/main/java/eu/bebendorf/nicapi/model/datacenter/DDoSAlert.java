package eu.bebendorf.nicapi.model.datacenter;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DDoSAlert {
    public int id;
    @SerializedName("resource_misuse_sig")
    public String resourceMisuseSignature;
    public String address;
    @SerializedName("duration_start")
    public Date durationStart;
    @SerializedName("duration_stop")
    public Date durationStop;
    @SerializedName("impact_bps")
    public long impactBytesPerSecond;
    @SerializedName("impact_pps")
    public long impactPacketsPerSecond;
    public String user;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
}
