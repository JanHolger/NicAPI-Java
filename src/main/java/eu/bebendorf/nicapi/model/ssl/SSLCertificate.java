package eu.bebendorf.nicapi.model.ssl;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

// I DON'T HAVE ANY REFERENCE DATA SO THIS IS INCOMPLETE
public class SSLCertificate implements NicModel {
    private transient NicAPI nicAPI;
    int id;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void cancel() throws NicAPIException {
        nicAPI.ssl().cancelCertificate(id);
    }
}
