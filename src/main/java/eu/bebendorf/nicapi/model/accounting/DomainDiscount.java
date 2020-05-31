package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DomainDiscount {
    public String tld;
    @SerializedName("start_date")
    public Date startDate;
    @SerializedName("end_date")
    public Date endDate;
    public int years;
    public double price;
    public String type;
}
