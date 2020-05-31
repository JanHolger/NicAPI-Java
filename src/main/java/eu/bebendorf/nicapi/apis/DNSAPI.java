package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.dns.DNSZone;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.request.DNSZoneRequest;

import static eu.bebendorf.nicapi.HttpClient.map;

public class DNSAPI {

    private final NicAPI nicAPI;

    public DNSAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public DNSZone[] getZones() throws NicAPIException {
        return DNSZone.init(NicModel.set(nicAPI.get("dns/zones").dataOrError(ZoneListResponse.class).zones, nicAPI));
    }

    public DNSZone getZone(String zone) throws NicAPIException {
        return NicModel.set(nicAPI.get("dns/zones/show", map("zone", zone)).dataOrError(ZoneSingleResponse.class).zone, nicAPI).init();
    }

    public DNSZone createZone(DNSZoneRequest request) throws NicAPIException {
        return NicModel.set(nicAPI.post("dns/zones/create", request).dataOrError(ZoneSingleResponse.class).zone, nicAPI).init();
    }

    public void refreshZone(String zone) throws NicAPIException {
        nicAPI.post("dns/zones/refresh", map("zone", zone)).orError();
    }

    public void deleteZone(String zone) throws NicAPIException {
        nicAPI.delete("dns/zones/delete", map("zone", zone)).orError();
    }

    public void createRecords(DNSZoneRequest request) throws NicAPIException {
        nicAPI.post("dns/zones/records/add", request).orError();
    }

    public void deleteRecords(DNSZoneRequest request) throws NicAPIException {
        nicAPI.delete("dns/zones/records/delete", request).orError();
    }

    private static class ZoneListResponse {
        public DNSZone[] zones;
    }

    private static class ZoneSingleResponse {
        public DNSZone zone;
    }

}
