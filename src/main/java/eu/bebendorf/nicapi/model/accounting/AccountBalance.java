package eu.bebendorf.nicapi.model.accounting;

import com.google.gson.annotations.SerializedName;

public class AccountBalance {
    double amount;
    @SerializedName("credit_limit")
    double creditLimit;
    @SerializedName("reserved_amount")
    double reservedAmount;
}
