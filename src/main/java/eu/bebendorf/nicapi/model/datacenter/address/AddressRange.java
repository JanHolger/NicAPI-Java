package eu.bebendorf.nicapi.model.datacenter.address;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

// I DON'T HAVE ANY REFERENCE DATA SO THIS IS INCOMPLETE
public class AddressRange implements NicModel {
    private transient NicAPI nicAPI;
    int id;
    public String getTrafficGraphImage(Date start, Date end) throws NicAPIException {
        return nicAPI.datacenter().traffic().getRangeGraphImage(id, start, end);
    }
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
}
