package eu.bebendorf.nicapi.request;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.model.Gender;

public class UserRequest {
    private String username;
    private String email;
    private String password;
    private Gender gender;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String city;
    private String position;
    private String street;
    private String number;
    private String zip;
    @SerializedName("country_id")
    private String country;
    @SerializedName("email_tech")
    private String emailTech;
    @SerializedName("email_contact")
    private String emailContact;
    @SerializedName("email_accounting")
    private String emailAccounting;
    @SerializedName("email_newsletter")
    private String emailNewsletter;
    @SerializedName("email_abuse")
    private String emailAbuse;
    public UserRequest(String username){
        this.username = username;
    }
    public UserRequest(){}
    public UserRequest email(String email){
        this.email = email;
        return this;
    }
    public UserRequest password(String password){
        this.password = password;
        return this;
    }
    public UserRequest gender(Gender gender){
        this.gender = gender;
        return this;
    }
    public UserRequest firstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public UserRequest lastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public UserRequest city(String city){
        this.city = city;
        return this;
    }
    public UserRequest position(String position){
        this.position = position;
        return this;
    }
    public UserRequest street(String street){
        this.street = street;
        return this;
    }
    public UserRequest number(String number){
        this.number = number;
        return this;
    }
    public UserRequest zip(String zip){
        this.zip = zip;
        return this;
    }
    public UserRequest country(String country){
        this.country = country;
        return this;
    }
    public UserRequest emailAbuse(String email){
        this.emailAbuse = email;
        return this;
    }
    public UserRequest emailAccounting(String email){
        this.emailAccounting = email;
        return this;
    }
    public UserRequest emailNewsletter(String email){
        this.emailNewsletter = email;
        return this;
    }
    public UserRequest emailTech(String email){
        this.emailTech = email;
        return this;
    }
    public UserRequest emailContact(String email) {
        this.emailContact = email;
        return this;
    }
}
