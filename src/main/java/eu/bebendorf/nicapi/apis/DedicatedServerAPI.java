package eu.bebendorf.nicapi.apis;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.datacenter.DedicatedServer;

import java.util.HashMap;

public class DedicatedServerAPI {

    private final NicAPI nicAPI;

    public DedicatedServerAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public DedicatedServer[] getServers() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/dedicated-servers").dataOrError(ServerListResponse.class).dedicatedServers, nicAPI);
    }

    public DedicatedServer getServer(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/dedicated-servers/"+id).dataOrError(ServerSingleResponse.class).dedicatedServer, nicAPI);
    }

    public void startServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/dedicated-servers/"+id+"/start", null).orError();
    }

    public void stopServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/dedicated-servers/"+id+"/stop", null).orError();
    }

    public void resetServer(int id) throws NicAPIException {
        nicAPI.post("datacenter/dedicated-servers/"+id+"/reset", null).orError();
    }

    public void resetKVM(int id) throws NicAPIException {
        nicAPI.post("datacenter/dedicated-servers/"+id+"/kvm-reset", null).orError();
    }

    public byte[] downloadKVM(int id) {
        return nicAPI.httpRequest("GET", "datacenter/dedicated-servers/"+id+"/kvm-download", new HashMap<>(), new HashMap<>(), null);
    }

    private static class ServerListResponse {
        @SerializedName("dedicated_servers")
        public DedicatedServer[] dedicatedServers;
    }

    private static class ServerSingleResponse {
        @SerializedName("dedicated_server")
        public DedicatedServer dedicatedServer;
    }

}
