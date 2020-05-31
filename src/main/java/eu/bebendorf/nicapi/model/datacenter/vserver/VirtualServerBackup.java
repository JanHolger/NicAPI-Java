package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class VirtualServerBackup implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    @SerializedName("vserver_id")
    public int virtualServerId;
    public String title;
    public String path;
    public long size;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void delete() throws NicAPIException {
        nicAPI.datacenter().virtualServer().deleteBackup(id);
    }
    public void restore() throws NicAPIException {
        nicAPI.datacenter().virtualServer().restoreBackup(virtualServerId, id);
    }
}
