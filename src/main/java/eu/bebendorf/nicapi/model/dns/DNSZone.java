package eu.bebendorf.nicapi.model.dns;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;
import eu.bebendorf.nicapi.model.domain.Domain;
import eu.bebendorf.nicapi.request.DNSZoneRequest;

import java.util.Date;

public class DNSZone implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String name;
    public String nameUnicode;
    public String serial;
    @SerializedName("hostmaster")
    public String hostMaster;
    public int refresh;
    public int retry;
    public int expire;
    public int ttl;
    @SerializedName("ns_first")
    public String ns1;
    @SerializedName("ns_second")
    public String ns2;
    public DNSRecord[] records;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public Domain[] domains;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
        for(Domain domain : domains)
            domain.setNicAPI(nicAPI);
    }
    public NicAPI getNicAPI(){
        return nicAPI;
    }
    public DNSZone init(){
        for(DNSRecord record : records)
            record.setZone(this);
        return this;
    }
    public void delete() throws NicAPIException {
        nicAPI.dns().deleteZone(name);
    }
    public void refresh() throws NicAPIException {
        nicAPI.dns().refreshZone(name);
    }
    public void createRecord(DNSRecordType type, int ttl, String name, String data) throws NicAPIException {
        nicAPI.dns().createRecords(new DNSZoneRequest(name).record(type, ttl, name, data));
    }
    public void deleteRecord(DNSRecordType type, String name) throws NicAPIException {
        deleteRecord(type, name, null);
    }
    public void deleteRecord(DNSRecordType type, String name, String data) throws NicAPIException {
        nicAPI.dns().deleteRecords(new DNSZoneRequest(name).record(type, null, name, data));
    }
    public static DNSZone[] init(DNSZone[] zones){
        for(DNSZone zone : zones)
            zone.init();
        return zones;
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
