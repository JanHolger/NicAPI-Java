package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.User;
import eu.bebendorf.nicapi.model.datacenter.address.Address;
import eu.bebendorf.nicapi.request.InstallationRequest;
import eu.bebendorf.nicapi.request.VirtualServerRequest;

import java.util.Date;

public class VirtualServer implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String title;
    public String hostname;
    public String type;
    public int memory;
    public int cores;
    public String template;
    public int disk;
    @SerializedName("start_at_boot")
    public int startAtBoot;
    public long traffic;
    @SerializedName("max_backups")
    public int maxBackups;
    @SerializedName("max_scheduled_tasks")
    public int maxScheduledTasks;
    public String password;
    @SerializedName("reseller_accounting")
    public int resellerAccounting;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public String user;
    public Address[] addresses;
    @SerializedName("addresses_v4")
    public int addressesV4;
    public VirtualServerNetwork[] networks;
    @SerializedName("vserver_host_hostname")
    public String vServerHostHostname;
    public void setNicAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
        if(addresses != null){
            for(Address address : addresses){
                address.setNicAPI(nicAPI);
            }
        }
    }
    public void start() throws NicAPIException {
        nicAPI.datacenter().virtualServer().startServer(id);
    }
    public void restart() throws NicAPIException {
        nicAPI.datacenter().virtualServer().restartServer(id);
    }
    public void shutdown() throws NicAPIException {
        nicAPI.datacenter().virtualServer().shutdownServer(id);
    }
    public void stop() throws NicAPIException {
        nicAPI.datacenter().virtualServer().stopServer(id);
    }
    public void delete() throws NicAPIException {
        nicAPI.datacenter().virtualServer().deleteServer(id);
    }
    public String getVNC() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getVNC(id);
    }
    public VirtualServerBackup createBackup() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().createBackup(id);
    }
    public void restoreBackup(int backupId) throws NicAPIException {
        nicAPI.datacenter().virtualServer().restoreBackup(id, backupId);
    }
    public VirtualServerBackup[] getBackups() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getBackups(id);
    }
    public void reinstall(InstallationRequest request) throws NicAPIException {
        nicAPI.datacenter().virtualServer().reinstallServer(id, request);
    }
    public VirtualServerScheduledTask scheduleTask(ScheduledTaskAction action, ScheduledTaskInterval interval) throws NicAPIException {
        return scheduleTask(action, interval, null);
    }
    public VirtualServerScheduledTask scheduleTask(ScheduledTaskAction action, ScheduledTaskInterval interval, Date firstExecution) throws NicAPIException {
        return nicAPI.datacenter().virtualServer().createScheduledTask(id, action, interval, firstExecution);
    }
    public VirtualServerScheduledTask[] getScheduledTasks() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getScheduledTasks(id);
    }
    public void deleteTask(int taskId) throws NicAPIException {
        nicAPI.datacenter().virtualServer().deleteScheduledTask(id, taskId);
    }
    public void upgrade(VirtualServerRequest request) throws NicAPIException {
        nicAPI.datacenter().virtualServer().upgradeServer(id, request);
        this.maxBackups = request.getBackups();
        this.cores = request.getCores();
        this.memory = request.getMemory();
        this.disk = request.getDisk();
    }
    public VirtualServerTask[] getTasks() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getTasks(id);
    }
    public VirtualServerStatus getStatus() throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getStatus(id);
    }
    public VirtualServerGraphEntry[] getGraph(String timeframe) throws NicAPIException {
        return getGraph(timeframe);
    }
    public VirtualServerGraphEntry[] getGraph(String timeframe, String cf) throws NicAPIException {
        return nicAPI.datacenter().virtualServer().getGraph(id, timeframe, cf);
    }
    public User getUser() throws NicAPIException {
        return nicAPI.account().getUser(user);
    }
}
