package eu.bebendorf.nicapi.model.datacenter.address;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class AddressNetwork implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String type;
    @SerializedName("addresses_count")
    public int totalAddresses;
    @SerializedName("addresses_free")
    public int freeAddresses;
    @SerializedName("address_first")
    public String firstAddress;
    @SerializedName("address_gateway")
    public String gateway;
    @SerializedName("address_last")
    public String lastAddress;
    public int netmask;
    public String user;
    public String getTrafficGraphImage(Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getNetworkGraphImage(id, start, end);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
