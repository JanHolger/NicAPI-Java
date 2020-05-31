package eu.bebendorf.nicapi.model.domain;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;

import java.util.Date;

public class Domain implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String sld;
    public String tld;
    public String name;
    public String nameUnicode;
    public String ownerC;
    public String adminC;
    public String techC;
    public String zoneC;
    public Date create;
    public Date expire;
    public Date delete;
    @SerializedName("suspended_at")
    public Date suspendedAt;
    @SerializedName("suspended_until")
    public Date suspendedUntil;
    @SerializedName("authinfo")
    public String authCode;
    public String status;
    @SerializedName("zone_id")
    public int zoneId;
    public String zone;
    public Nameservers nameservers;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
        if(nameservers != null)
            nameservers.setNicAPI(nicAPI);
    }
    public static class Nameservers implements NicModel {
        public Nameserver ns1;
        public Nameserver ns2;
        public Nameserver ns3;
        public Nameserver ns4;
        public Nameserver ns5;
        public void setNicAPI(NicAPI nicAPI) {
            if(ns1 != null)
                ns1.setNicAPI(nicAPI);
            if(ns2 != null)
                ns2.setNicAPI(nicAPI);
            if(ns3 != null)
                ns3.setNicAPI(nicAPI);
            if(ns4 != null)
                ns4.setNicAPI(nicAPI);
            if(ns5 != null)
                ns5.setNicAPI(nicAPI);
        }
    }
    public void delete() throws NicAPIException {
        nicAPI.domain().deleteDomain(name);
    }
    public void delete(Date date) throws NicAPIException {
        nicAPI.domain().deleteDomain(name, date);
    }
    public void undelete() throws NicAPIException {
        nicAPI.domain().undeleteDomain(name);
    }
    public void restore() throws NicAPIException {
        nicAPI.domain().restoreDomain(name);
    }
    public String authCode() throws NicAPIException {
        if(authCode == null)
            authCode = nicAPI.domain().createAuthCode(name);
        return authCode;
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }

}
