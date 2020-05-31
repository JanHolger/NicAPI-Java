package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.datacenter.address.Address;
import eu.bebendorf.nicapi.model.datacenter.traffic.TrafficEntry;
import eu.bebendorf.nicapi.model.datacenter.address.AddressNetwork;
import eu.bebendorf.nicapi.model.datacenter.address.AddressRange;

import java.util.Date;

import static eu.bebendorf.nicapi.HttpClient.map;

public class AddressAPI {

    private final NicAPI nicAPI;

    public AddressAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public Address[] getAddresses() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/addresses").dataOrError(AddressListResponse.class).addresses, nicAPI);
    }

    public Address getAddress(String address) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        return NicModel.set(nicAPI.get("datacenter/addresses/"+address).dataOrError(AddressSingleResponse.class).address, nicAPI);
    }

    public AddressRange[] getRanges() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/address-ranges").dataOrError(RangeListResponse.class).ranges, nicAPI);
    }

    public AddressRange getRange(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/address-ranges/"+id).dataOrError(RangeSingleResponse.class).range, nicAPI);
    }

    public AddressNetwork[] getNetworks() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/address-networks").dataOrError(NetworkListResponse.class).networks, nicAPI);
    }

    public AddressNetwork getNetwork(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/address-networks/"+id).dataOrError(NetworkSingleResponse.class).network, nicAPI);
    }

    public String getRangeTrafficGraphImage(int id, Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getRangeGraphImage(id, start, end);
    }

    public String getNetworkTrafficGraphImage(int id, Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getNetworkGraphImage(id, start, end);
    }

    public void createRDNS(String address) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        nicAPI.post("datacenter/addresses/"+address+"/rdns/create", null).orError();
    }

    public void deleteRDNS(String address) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        nicAPI.delete("datacenter/addresses/"+address+"/rdns/delete").orError();
    }

    public void reserveAddress(String address, String reason) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        nicAPI.post("datacenter/addresses/"+address+"/reserve", map("reason", reason)).orError();
    }

    public void unreserveAddress(String address) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        nicAPI.post("datacenter/addresses/"+address+"/unreserve", null).orError();
    }

    public TrafficEntry[] getTrafficHistory(String address) throws NicAPIException {
        return nicAPI.datacenter().traffic().getAddressHistory(address);
    }

    public String getTrafficGraphImage(String address, Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getAddressGraphImage(address, start, end);
    }

    private static class AddressListResponse {
        public Address[] addresses;
    }

    private static class AddressSingleResponse {
        public Address address;
    }

    private static class NetworkListResponse {
        public AddressNetwork[] networks;
    }

    private static class NetworkSingleResponse {
        public AddressNetwork network;
    }

    private static class RangeListResponse {
        public AddressRange[] ranges;
    }

    private static class RangeSingleResponse {
        public AddressRange range;
    }

}
