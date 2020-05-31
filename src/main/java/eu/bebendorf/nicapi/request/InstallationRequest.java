package eu.bebendorf.nicapi.request;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.model.datacenter.pxe.PXETemplate;

public class InstallationRequest {
    @SerializedName("mac_address")
    private String macAddress;
    private String address;
    @SerializedName("vserver_template")
    private String template;
    private String hostname;
    private String password;
    @SerializedName("support_ssh_key")
    private Boolean supportSSHKey;
    @SerializedName("raid_level")
    private Integer raidLevel;
    @SerializedName("raid_disk_count")
    private Integer raidDiskCount;
    @SerializedName("network_name")
    private String networkName;
    private String[] commands;
    public InstallationRequest(PXETemplate template, String password){
        this(template.key, password);
    }
    public InstallationRequest(String template, String password){
        this.template = template;
        this.password = password;
    }
    public InstallationRequest mac(String macAddress){
        this.macAddress = macAddress;
        return this;
    }
    public InstallationRequest address(String address){
        this.address = address;
        return this;
    }
    public InstallationRequest commands(String... commands){
        this.commands = commands;
        return this;
    }
    public InstallationRequest networkName(String networkName){
        this.networkName = networkName;
        return this;
    }
    public InstallationRequest hostname(String hostname){
        this.hostname = hostname;
        return this;
    }
    public InstallationRequest raid(int level, int diskCount){
        this.raidLevel = level;
        this.raidDiskCount = diskCount;
        return this;
    }
    public InstallationRequest supportSSH(boolean supportSSHKey){
        this.supportSSHKey = supportSSHKey;
        return this;
    }
}
