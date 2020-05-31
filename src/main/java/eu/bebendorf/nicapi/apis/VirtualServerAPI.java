package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.*;
import eu.bebendorf.nicapi.model.datacenter.address.Address;
import eu.bebendorf.nicapi.model.datacenter.vserver.*;
import eu.bebendorf.nicapi.request.InstallationRequest;
import eu.bebendorf.nicapi.request.VirtualServerRequest;

import java.util.Date;

import static eu.bebendorf.nicapi.HttpClient.map;

public class VirtualServerAPI {

    private final NicAPI nicAPI;

    public VirtualServerAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public VirtualServer createServer(VirtualServerRequest request) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/vservers/create", request).dataOrError(VServerSingleResponse.class).vserver, nicAPI);
    }

    public void upgradeServer(int id, VirtualServerRequest request) throws NicAPIException {
        nicAPI.put("datacenter/vservers/"+id+"/upgrade", request).orError();
    }

    public VirtualServer[] getServers() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/vservers").dataOrError(VServerListResponse.class).vservers, nicAPI);
    }

    public VirtualServer getServer(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/vservers/"+id).dataOrError(VServerSingleResponse.class).vserver, nicAPI);
    }

    public void startServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/vservers/"+id+"/start", null).orError();
    }

    public void restartServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/vservers/"+id+"/restart", null).orError();
    }

    public void shutdownServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/vservers/"+id+"/shutdown", null).orError();
    }

    public void stopServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/vservers/"+id+"/stop", null).orError();
    }

    public void deleteServer(int id) throws NicAPIException {
        nicAPI.delete("datacenter/vservers/"+id+"/delete", null).orError();
    }

    public VirtualServerBackup createBackup(int id) throws NicAPIException {
        return NicModel.set(nicAPI.delete("datacenter/vservers/"+id+"/delete", null).dataOrError(BackupSingleResponse.class).backup, nicAPI);
    }

    public void deleteBackup(int id) throws NicAPIException {
        nicAPI.delete("datacenter/vservers/backups/"+id+"/delete", null).orError();
    }

    public void restoreBackup(int virtualServerId, int backupId) throws NicAPIException {
        nicAPI.post("datacenter/vservers/backups/"+backupId+"/restore", map("vserver_id", virtualServerId)).orError();
    }

    public VirtualServerBackup[] getBackups(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/vservers/"+id+"/backups").dataOrError(BackupListResponse.class).backups, nicAPI);
    }

    public VirtualServerNetwork[] getNetworks(int id) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/networks").dataOrError(NetworkListResponse.class).networks;
    }

    public void reinstallServer(int id, InstallationRequest request) throws NicAPIException {
        nicAPI.post("datacenter/vservers/"+id+"/reinstall", request).orError();
    }

    public VirtualServerScheduledTask createScheduledTask(int id, ScheduledTaskAction action, ScheduledTaskInterval interval, Date firstExecution) throws NicAPIException {
        return createScheduledTask(id, action.toString(), interval.toString(), firstExecution);
    }

    public VirtualServerScheduledTask createScheduledTask(int id, String action, String interval, Date firstExecution) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/vservers/scheduled-tasks/create", map(
                "vserver_id", id,
                "action", action,
                "interval", interval,
                "first_execution", firstExecution
        )).dataOrError(ScheduledTaskSingleResponse.class).task, nicAPI);
    }

    public VirtualServerScheduledTask[] getScheduledTasks(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/vservers/"+id+"/scheduledTasks").dataOrError(ScheduledTaskListResponse.class).tasks, nicAPI);
    }

    public void deleteScheduledTask(int virtualServerId, int taskId) throws NicAPIException {
        nicAPI.delete("datacenter/vservers/scheduled-tasks/"+taskId+"/delete", map("vserver_id", virtualServerId)).orError();
    }

    public String getVNC(int id) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/vnc", null).dataOrError(VNCResponse.class).html;
    }

    public VirtualServerTask[] getTasks(int id) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/tasks").dataOrError(TaskListResponse.class).tasks;
    }

    public Address[] getAddresses(int id) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/addresses").dataOrError(AddressListResponse.class).addresses;
    }

    public VirtualServerStatus getStatus(int id) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/status").dataOrError(StatusResponse.class).status;
    }

    public VirtualServerGraphEntry[] getGraph(int id, String timeframe) throws NicAPIException {
        return getGraph(id, timeframe, null);
    }

    public VirtualServerGraphEntry[] getGraph(int id, String timeframe, String cf) throws NicAPIException {
        return nicAPI.get("datacenter/vservers/"+id+"/graph", map("timeframe", timeframe, "cf", cf)).dataOrError(GraphResponse.class).graph.data;
    }

    private static class StatusResponse {
        public VirtualServerStatus status;
    }

    private static class VServerSingleResponse {
        public VirtualServer vserver;
    }

    private static class VServerListResponse {
        public VirtualServer[] vservers;
    }

    private static class VNCResponse {
        public String html;
    }

    private static class BackupSingleResponse {
        public VirtualServerBackup backup;
    }

    private static class BackupListResponse {
        public VirtualServerBackup[] backups;
    }

    private static class NetworkListResponse {
        public VirtualServerNetwork[] networks;
    }

    private static class ScheduledTaskSingleResponse {
        public VirtualServerScheduledTask task;
    }

    private static class ScheduledTaskListResponse {
        public VirtualServerScheduledTask[] tasks;
    }

    private static class TaskListResponse {
        public VirtualServerTask[] tasks;
    }

    private static class AddressListResponse {
        public Address[] addresses;
    }

    private static class GraphResponse {
        public Graph graph;
        public static class Graph {
            public VirtualServerGraphEntry[] data;
        }
    }

}
