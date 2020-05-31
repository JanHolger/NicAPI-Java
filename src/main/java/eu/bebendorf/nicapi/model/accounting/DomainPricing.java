package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

public class DomainPricing {
    public String tld;
    public String county;
    @SerializedName("country_code")
    public String countryCode;
    public double create;
    public double transfer;
    public double renew;
    @SerializedName("ownerchange")
    public double ownerChange;
    public double update;
    public double restore;
}
