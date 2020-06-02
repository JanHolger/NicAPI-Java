package eu.bebendorf.nicapi.model.domain;

import com.google.gson.annotations.SerializedName;

public class DomainTLD {
    public int id;
    public String tld;
    @SerializedName("country_code")
    public String countryCode;
    public String country;
}
