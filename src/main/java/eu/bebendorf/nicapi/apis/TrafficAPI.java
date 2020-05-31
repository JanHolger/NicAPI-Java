package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.datacenter.traffic.TrafficEntry;
import eu.bebendorf.nicapi.model.datacenter.traffic.TrafficPool;

import java.util.Date;

import static eu.bebendorf.nicapi.HttpClient.map;

public class TrafficAPI {

    private final NicAPI nicAPI;

    public TrafficAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }

    public TrafficEntry[] getTraffic() throws NicAPIException {
        return nicAPI.get("datacenter/traffic").dataOrError(TrafficListResponse.class).traffic;
    }

    public TrafficPool[] getPools() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/traffic/pools").dataOrError(TrafficPoolListResponse.class).pools, nicAPI);
    }

    public TrafficPool getPool(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/traffic/pools/"+id).dataOrError(TrafficPoolSingleResponse.class).pool, nicAPI);
    }

    public String getPoolGraphImage(int id, Date start, Date end) throws NicAPIException {
        return nicAPI.get("datacenter/traffic/pools/"+id+"/graph", map("start", start, "end", end)).dataOrError(TrafficGraphResponse.class).src;
    }

    public String getAddressGraphImage(String address, Date start, Date end) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        return nicAPI.get("datacenter/addresses/"+address+"/graph", map("start", start, "end", end)).dataOrError(TrafficGraphResponse.class).src;
    }

    public String getRangeGraphImage(int id, Date start, Date end) throws NicAPIException {
        return nicAPI.get("datacenter/address-ranges/"+id+"/graph", map("start", start, "end", end)).dataOrError(TrafficGraphResponse.class).src;
    }

    public String getNetworkGraphImage(int id, Date start, Date end) throws NicAPIException {
        return nicAPI.get("datacenter/address-networks/"+id+"/graph", map("start", start, "end", end)).dataOrError(TrafficGraphResponse.class).src;
    }

    public TrafficEntry[] getAddressHistory(String address) throws NicAPIException {
        if(address.contains("/") || address.contains("?"))
            throw new RuntimeException("Invalid characters in parameter address!");
        return nicAPI.get("datacenter/addresses/"+address+"/traffic").dataOrError(TrafficListResponse.class).traffic;
    }

    public TrafficEntry[] getPoolHistory(int id) throws NicAPIException {
        return nicAPI.get("datacenter/traffic/pools/"+id+"/history").dataOrError(TrafficHistoryResponse.class).histories;
    }

    private static class TrafficGraphResponse {
        public String src;
    }

    private static class TrafficHistoryResponse {
        public TrafficEntry[] histories;
    }

    private static class TrafficListResponse {
        public TrafficEntry[] traffic;
    }

    private static class TrafficPoolListResponse {
        public TrafficPool[] pools;
    }

    private static class TrafficPoolSingleResponse {
        public TrafficPool pool;
    }

}
