package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.HttpClient;

import java.util.Map;

public class VirtualServerPricing {
    public double cores;
    public double memory;
    public double disk;
    @SerializedName("addresses_v4")
    public double addressesV4;
    public double backups;
    public double total;
    private JsonObject options;
    public Map<String, Option> getOptions(){
        return HttpClient.toMap(options, Option.class);
    }
    public static class Option {
        public int id;
        public String unit;
        @SerializedName("max_units")
        public int maxUnits;
        @SerializedName("min_units")
        public int minUnits;
        public int step;
        @SerializedName("free_units")
        public int freeUnits;
        public double price;
    }
}
