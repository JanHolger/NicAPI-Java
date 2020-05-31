package eu.bebendorf.nicapi.request;

import eu.bebendorf.nicapi.model.dns.DNSRecord;
import eu.bebendorf.nicapi.model.dns.DNSRecordType;

import java.util.ArrayList;
import java.util.List;

public class DNSZoneRequest {
    private String zone;
    private String hostmaster;
    private Integer refresh;
    private Integer expire;
    private Integer retry;
    private Integer ttl;
    private List<DNSRecord> records;
    private String user;
    public DNSZoneRequest(String zone){
        this.zone = zone;
    }
    public DNSZoneRequest hostMaster(String hostmaster){
        this.hostmaster = hostmaster;
        return this;
    }
    public DNSZoneRequest defaults(){
        refresh = 43200;
        expire = 1209600;
        retry = 7200;
        ttl = 300;
        records = new ArrayList<>();
        return this;
    }
    public DNSZoneRequest refresh(int refresh){
        this.refresh = refresh;
        return this;
    }
    public DNSZoneRequest expire(int expire){
        this.expire = expire;
        return this;
    }
    public DNSZoneRequest retry(int retry){
        this.retry = retry;
        return this;
    }
    public DNSZoneRequest ttl(int ttl){
        this.ttl = ttl;
        return this;
    }
    public DNSZoneRequest record(DNSRecordType type, Integer ttl, String name, String data){
        DNSRecord record = new DNSRecord();
        record.type = type;
        record.data = data;
        record.name = name;
        record.ttl = ttl;
        if(records == null)
            records = new ArrayList<>();
        records.add(record);
        return this;
    }
    public DNSZoneRequest a(Integer ttl, String name, String data){
        return record(DNSRecordType.A, ttl, name, data);
    }
    public DNSZoneRequest aaaa(Integer ttl, String name, String data){
        return record(DNSRecordType.AAAA, ttl, name, data);
    }
    public DNSZoneRequest srv(Integer ttl, String name, String data){
        return record(DNSRecordType.SRV, ttl, name, data);
    }
    public DNSZoneRequest txt(Integer ttl, String name, String data){
        return record(DNSRecordType.TXT, ttl, name, data);
    }
    public DNSZoneRequest mx(Integer ttl, String name, String data){
        return record(DNSRecordType.MX, ttl, name, data);
    }
    public DNSZoneRequest cname(Integer ttl, String name, String data){
        return record(DNSRecordType.CNAME, ttl, name, data);
    }
}
