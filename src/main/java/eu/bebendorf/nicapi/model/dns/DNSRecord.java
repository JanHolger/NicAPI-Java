package eu.bebendorf.nicapi.model.dns;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPIException;

import java.util.Date;

public class DNSRecord {
    private transient DNSZone zone;
    public Integer id;
    public String name;
    public Integer ttl;
    public DNSRecordType type;
    public String data;
    @SerializedName("zone_id")
    public String zoneId;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public void setZone(DNSZone zone){
        this.zone = zone;
    }
    public void delete() throws NicAPIException {
        zone.deleteRecord(type, name, data);
    }
}
