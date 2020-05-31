package eu.bebendorf.nicapi.model.account;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.Gender;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.request.UserRequest;

import java.util.Date;

public class User implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String username;
    public String company;
    public Gender gender;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    public String position;
    public String email;
    @SerializedName("email_tech")
    public String emailTech;
    @SerializedName("email_contact")
    public String emailContact;
    @SerializedName("email_accounting")
    public String emailAccounting;
    @SerializedName("email_newsletter")
    public String emailNewsletter;
    @SerializedName("email_abuse")
    public String emailAbuse;
    public String street;
    public String number;
    public String zip;
    public String city;
    public String country;
    public String phone;
    public String fax;
    @SerializedName("credit_limit")
    public double creditLimit;
    @SerializedName("amount")
    public double balance;
    @SerializedName("next_billing_circle")
    public Date nextBillingCycle;
    public Type type;
    public State state;
    @SerializedName("parent_id")
    public Integer parentId;
    public User parent;
    @SerializedName("created_at")
    public Date createdAt;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void lock() throws NicAPIException {
        nicAPI.account().lockUser(username);
    }
    public void unlock() throws NicAPIException {
        nicAPI.account().unlockUser(username);
    }
    public APIToken createToken() throws NicAPIException {
        return nicAPI.account().createAPIToken(username, null);
    }
    public APIToken createToken(Date validUntil) throws NicAPIException {
        return nicAPI.account().createAPIToken(username, validUntil);
    }
    public UserPermission[] getPermissions() throws NicAPIException {
        return nicAPI.account().getPermissions(username);
    }
    public void setPermissions(UserPermission... permissions) throws NicAPIException {
        nicAPI.account().setPermissions(username, permissions);
    }
    public void setPermissions(String... permissions) throws NicAPIException {
        nicAPI.account().setPermissions(username, permissions);
    }
    public User update(UserRequest request) throws NicAPIException {
        return nicAPI.account().updateUser(username, request);
    }
    public void update() throws NicAPIException {
        update(new UserRequest()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .city(city)
                .street(street)
                .number(number)
                .zip(zip)
                .emailAbuse(emailAbuse)
                .emailAccounting(emailAccounting)
                .emailNewsletter(emailNewsletter)
                .emailTech(emailTech)
                .emailContact(emailContact)
                .country(country)
                .position(position)
        );
    }
    public void changePassword(String password) throws NicAPIException {
        update(new UserRequest().password(password));
    }
    public enum Type {
        CUSTOMER
    }
    public enum State {
        ACTIVATED
    }
}
