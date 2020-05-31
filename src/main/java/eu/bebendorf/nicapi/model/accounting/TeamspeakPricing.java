package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

public class TeamspeakPricing {
    @SerializedName("traffic_external")
    public int trafficExternal;
    public double unit;
    public double total;
    public boolean isTrafficExternal(){
        return trafficExternal != 0;
    }
}
