package eu.bebendorf.nicapi.request;

import com.google.gson.annotations.SerializedName;

public class VirtualServerRequest {
    private int runtime = 1;
    private String password;
    private int cores = 1;
    private int memory = 1024;
    private int disk = 10;
    @SerializedName("addresses_v4")
    private int addressesV4 = 1;
    private int backups = 1;
    @SerializedName("vserver_template")
    private String template = "DEBIAN_9";
    private String hostname;
    private String datacenter;
    @SerializedName("reseller_accounting")
    private Boolean resellerAccounting;
    private Integer vlan;
    @SerializedName("addresses_specific")
    private String[] addressesSpecific;
    public VirtualServerRequest(){}
    public VirtualServerRequest(int cores, int memory, int disk){
        this.cores = cores;
        this.memory = memory;
        this.disk = disk;
    }
    public VirtualServerRequest addresses(int addressesV4){
        this.addressesV4 = addressesV4;
        return this;
    }
    public VirtualServerRequest addresses(String... addresses){
        this.addressesSpecific = addresses;
        this.addressesV4 = addresses.length;
        return this;
    }
    public VirtualServerRequest backups(int backups){
        this.backups = backups;
        return this;
    }
    public VirtualServerRequest runtime(int runtime){
        this.runtime = runtime;
        return this;
    }
    public VirtualServerRequest password(String password){
        this.password = password;
        return this;
    }
    public VirtualServerRequest vlan(int vlan){
        this.vlan = vlan;
        return this;
    }
    public VirtualServerRequest cores(int cores){
        this.cores = cores;
        return this;
    }
    public VirtualServerRequest memory(int memory){
        this.memory = memory;
        return this;
    }
    public VirtualServerRequest disk(int disk){
        this.disk = disk;
        return this;
    }
    public VirtualServerRequest hostname(String hostname){
        this.hostname = hostname;
        return this;
    }
    public VirtualServerRequest template(String template){
        this.template = template;
        return this;
    }
    public VirtualServerRequest template(VirtualServerTemplate template){
        this.template = template.toString();
        return this;
    }

    public int getBackups() {
        return backups;
    }

    public int getCores() {
        return cores;
    }

    public int getMemory() {
        return memory;
    }

    public int getDisk() {
        return disk;
    }
}
