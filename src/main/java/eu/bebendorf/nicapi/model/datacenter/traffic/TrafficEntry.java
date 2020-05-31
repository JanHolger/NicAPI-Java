package eu.bebendorf.nicapi.model.datacenter.traffic;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TrafficEntry {
    public String address;
    public Date date;
    @SerializedName("bytes_in")
    public long bytesIn;
    @SerializedName("bytes_out")
    public long bytesOut;
    @SerializedName("bytes_sum")
    public long bytesSum;
}
