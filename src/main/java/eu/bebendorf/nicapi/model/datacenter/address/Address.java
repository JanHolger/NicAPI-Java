package eu.bebendorf.nicapi.model.datacenter.address;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.datacenter.traffic.TrafficEntry;

import java.util.Date;

public class Address implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String address;
    public String rdns;
    public String range;
    public String reserved;
    public String type;
    public String user;
    public int network;
    @SerializedName("address_gateway")
    public String addressGateway;
    @SerializedName("address_broadcast")
    public String addressBroadcast;
    @SerializedName("address_network")
    public String addressNetwork;
    @SerializedName("address_type")
    public String addressType;
    @SerializedName("network_netmask")
    public int networkNetmask;
    public void reserve(String reason) throws NicAPIException {
        nicAPI.datacenter().address().reserveAddress(address, reason);
    }
    public void unreserve() throws NicAPIException {
        nicAPI.datacenter().address().unreserveAddress(address);
    }
    public void createRDNS() throws NicAPIException {
        nicAPI.datacenter().address().createRDNS(address);
    }
    public void deleteRDNS() throws NicAPIException {
        nicAPI.datacenter().address().deleteRDNS(address);
    }
    public TrafficEntry[] getTrafficHistory() throws NicAPIException {
        return nicAPI.datacenter().traffic().getAddressHistory(address);
    }
    public String getTrafficGraphImage(Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getAddressGraphImage(address, start, end);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
