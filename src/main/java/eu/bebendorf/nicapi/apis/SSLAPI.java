package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.ssl.SSLCertificate;

// I DON'T HAVE ANY REFERENCE DATA SO THIS IS INCOMPLETE
public class SSLAPI {

    private final NicAPI nicAPI;

    public SSLAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public SSLCertificate[] getCertificates() throws NicAPIException {
        return NicModel.set(nicAPI.get("ssl/ssl").dataOrError(CertificateListResponse.class).certificates, nicAPI);
    }

    public SSLCertificate getCertificate(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("ssl/ssl/"+id).dataOrError(CertificateSingleResponse.class).certificate, nicAPI);
    }

    public void cancelCertificate(int id) throws NicAPIException {
        nicAPI.delete("ssl/ssl/"+id+"/cancel").orError();
    }

    private static class CertificateListResponse {
        public SSLCertificate[] certificates;
    }

    private static class CertificateSingleResponse {
        public SSLCertificate certificate;
    }

}
