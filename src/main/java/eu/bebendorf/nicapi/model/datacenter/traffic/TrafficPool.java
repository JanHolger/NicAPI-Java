package eu.bebendorf.nicapi.model.datacenter.traffic;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class TrafficPool implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public int amount;
    @SerializedName("accounting_step")
    public int accountingStep;
    @SerializedName("bytes_in")
    public long bytesIn;
    @SerializedName("bytes_out")
    public long bytesOut;
    @SerializedName("bytes_sum")
    public long bytesSum;
    @SerializedName("traffic_exceeded")
    public long trafficExceeded;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public String getGraphImage(Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getPoolGraphImage(id, start, end);
    }
    public TrafficEntry[] getHistory() throws NicAPIException {
        return nicAPI.datacenter().traffic().getPoolHistory(id);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
