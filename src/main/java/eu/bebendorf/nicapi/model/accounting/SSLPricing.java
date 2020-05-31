package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

public class SSLPricing {
    public int id;
    public double price;
    @SerializedName("certificate_type")
    public String certificateType;
    public int runtime;
}
