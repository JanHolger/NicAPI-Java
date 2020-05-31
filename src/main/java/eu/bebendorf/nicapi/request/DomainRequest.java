package eu.bebendorf.nicapi.request;

import com.google.gson.annotations.SerializedName;

public class DomainRequest {
    @SerializedName("domainName")
    private String name;
    private String ownerC;
    private String adminC;
    private String techC;
    private String zoneC;
    private String ns1;
    private String ns2;
    private String ns3;
    private String ns4;
    private String ns5;
    private String user;
    private int years = 1;
    @SerializedName("create_zone")
    private boolean createZone = true;
    @SerializedName("authinfo")
    private String authInfo;
    public DomainRequest(String name){
        this.name = name;
    }
    public DomainRequest ownerC(String handle){
        this.ownerC = handle;
        return this;
    }
    public DomainRequest adminC(String handle){
        this.adminC = handle;
        return this;
    }
    public DomainRequest techC(String handle){
        this.techC = handle;
        return this;
    }
    public DomainRequest zoneC(String handle){
        this.zoneC = handle;
        return this;
    }
    public DomainRequest handles(String handle){
        return handles(handle, handle, handle, handle);
    }
    public DomainRequest handles(String ownerC, String adminC, String techC, String zoneC){
        this.ownerC = ownerC;
        this.adminC = adminC;
        this.techC = techC;
        this.zoneC = zoneC;
        return this;
    }
    public DomainRequest nameservers(String... nameservers){
        this.ns1 = nameservers[0];
        this.ns2 = nameservers[1];
        if(nameservers.length>2)
            this.ns3 = nameservers[2];
        if(nameservers.length>3)
            this.ns4 = nameservers[3];
        if(nameservers.length>4)
            this.ns5 = nameservers[4];
        return this;
    }
    public DomainRequest authCode(String authInfo){
        this.authInfo = authInfo;
        return this;
    }
    public DomainRequest years(int years){
        this.years = years;
        return this;
    }
    public DomainRequest createZone(boolean createZone){
        this.createZone = createZone;
        return this;
    }
}
