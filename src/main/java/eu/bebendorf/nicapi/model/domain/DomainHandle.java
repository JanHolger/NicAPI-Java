package eu.bebendorf.nicapi.model.domain;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.Gender;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;

import java.util.Date;

public class DomainHandle implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String handle;
    public String type;
    public Gender sex;
    @SerializedName("firstname")
    public String firstName;
    @SerializedName("lastname")
    public String lastName;
    public String organisation;
    public String street;
    @SerializedName("number")
    public String streetNumber;
    @SerializedName("postcode")
    public String postCode;
    public String city;
    public String region;
    public String country;
    public String phone;
    public String fax;
    public String email;
    @SerializedName("idcard")
    public String idCard;
    @SerializedName("idcardissuedate")
    public Date idCardIssueDate;
    @SerializedName("idcardauthority")
    public String idCardAuthority;
    @SerializedName("taxnr")
    public String taxNumber;
    @SerializedName("vatnr")
    public String vatNumber;
    @SerializedName("dateofbirth")
    public Date dateOfBirth;
    @SerializedName("countryofbirth")
    public String countryOfBirth;
    @SerializedName("placeofbirth")
    public String placeOfBirth;
    @SerializedName("regionofbirth")
    public String regionOfBirth;
    @SerializedName("registrationnumber")
    public String registrationNumber;
    public int protection;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void delete() throws NicAPIException {
        nicAPI.domain().deleteHandle(handle);
    }
    public void update() throws NicAPIException {
        nicAPI.domain().updateHandle(this);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
