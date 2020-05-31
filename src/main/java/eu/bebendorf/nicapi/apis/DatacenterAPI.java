package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.datacenter.DDoSAlert;

public class DatacenterAPI {

    private final NicAPI nicAPI;
    private final VirtualServerAPI virtualServer;
    private final PXEAPI pxe;
    private final TrafficAPI traffic;
    private final DedicatedServerAPI dedicatedServer;
    private final AddressAPI address;

    public DatacenterAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
        this.virtualServer = new VirtualServerAPI(nicAPI);
        this.pxe = new PXEAPI(nicAPI);
        this.traffic = new TrafficAPI(nicAPI);
        this.dedicatedServer = new DedicatedServerAPI(nicAPI);
        this.address = new AddressAPI(nicAPI);
    }

    public VirtualServerAPI virtualServer(){
        return virtualServer;
    }

    public PXEAPI pxe(){
        return pxe;
    }

    public TrafficAPI traffic(){
        return traffic;
    }

    public DedicatedServerAPI dedicatedServer(){
        return dedicatedServer;
    }

    public AddressAPI address(){
        return address;
    }

    public DDoSAlert[] getDDoSAlerts() throws NicAPIException {
        return nicAPI.get("datacenter/ddos-alerts").dataOrError(DDoSListResponse.class).alerts;
    }

    public DDoSAlert getDDoSAlert(int id) throws NicAPIException {
        return nicAPI.get("datacenter/ddos-alerts/"+id).dataOrError(DDoSSingleResponse.class).alert;
    }

    private static class DDoSListResponse {
        public DDoSAlert[] alerts;
    }

    private static class DDoSSingleResponse {
        public DDoSAlert alert;
    }

}
