package eu.bebendorf.nicapi.model.datacenter;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

public class DedicatedServer implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String title;
    @SerializedName("custom_title")
    public String customTitle;
    public String cpi;
    public String memory;
    public String disk;
    public int uplink;
    // NOT SURE ABOUT THE DATATYPE
    public JsonElement traffic;
    public String user;
    public void start() throws NicAPIException {
        nicAPI.datacenter().dedicatedServer().startServer(id);
    }
    public void stop() throws NicAPIException {
        nicAPI.datacenter().dedicatedServer().stopServer(id);
    }
    public void reset() throws NicAPIException {
        nicAPI.datacenter().dedicatedServer().resetServer(id);
    }
    public void resetKVM() throws NicAPIException {
        nicAPI.datacenter().dedicatedServer().resetKVM(id);
    }
    public byte[] downloadKVM(){
        return nicAPI.datacenter().dedicatedServer().downloadKVM(id);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
