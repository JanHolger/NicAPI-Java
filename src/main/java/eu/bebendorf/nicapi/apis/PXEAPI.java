package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.datacenter.pxe.PXEInstallation;
import eu.bebendorf.nicapi.model.datacenter.pxe.PXEOperation;
import eu.bebendorf.nicapi.model.datacenter.pxe.PXETemplate;
import eu.bebendorf.nicapi.request.InstallationRequest;

import static eu.bebendorf.nicapi.HttpClient.map;

public class PXEAPI {

    private final NicAPI nicAPI;

    public PXEAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public PXETemplate[] getTemplates() throws NicAPIException {
        return nicAPI.get("datacenter/pxeinstallations/templates").dataOrError(TemplateListResponse.class).templates;
    }

    public PXEOperation[] getOperations() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/pxeoperations").dataOrError(OperationListResponse.class).operations, nicAPI);
    }

    public PXEOperation getOperation(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/pxeoperations/"+id).dataOrError(OperationSingleResponse.class).operation, nicAPI);
    }

    public void cancelOperation(int id) throws NicAPIException {
        nicAPI.post("datacenter/pxeoperations/"+id+"/cancel", null).orError();
    }

    public PXEOperation createDiskExtendOperation(String macAddress, boolean isRaid) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/pxeoperations/create", map(
                "actions", "diskExtend",
                "mac_address", macAddress,
                "is_raid", isRaid
        )).dataOrError(OperationSingleResponse.class).operation, nicAPI);
    }

    public PXEOperation createResetNetworkOperation(String macAddress, String networkName, String address) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/pxeoperations/create", map(
                "action", "resetNetwork",
                "mac_address", macAddress,
                "address", address,
                "network_name", networkName
        )).dataOrError(OperationSingleResponse.class).operation, nicAPI);
    }

    public PXEOperation createChangePasswordOperation(String macAddress, String password) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/pxeoperations/create", map(
                "action", "changePassword",
                "mac_address", macAddress,
                "password", password
        )).dataOrError(OperationSingleResponse.class).operation, nicAPI);
    }

    public PXEInstallation[] getInstallations() throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/pxeinstallations").dataOrError(InstallationListResponse.class).installations, nicAPI);
    }

    public PXEInstallation getInstallation(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("datacenter/pxeinstallations/"+id).dataOrError(InstallationSingleResponse.class).installation, nicAPI);
    }

    public void cancelInstallation(int id) throws NicAPIException {
        nicAPI.post("datacenter/pxeinstallations/"+id+"/cancel", null).orError();
    }

    public PXEInstallation createInstallation(InstallationRequest request) throws NicAPIException {
        return NicModel.set(nicAPI.post("datacenter/pxeinstallations/create", null).dataOrError(InstallationSingleResponse.class).installation, nicAPI);
    }

    private static class OperationListResponse {
        public PXEOperation[] operations;
    }

    private static class OperationSingleResponse {
        public PXEOperation operation;
    }

    private static class InstallationListResponse {
        public PXEInstallation[] installations;
    }

    private static class InstallationSingleResponse {
        public PXEInstallation installation;
    }

    private static class TemplateListResponse {
        public PXETemplate[] templates;
    }

}
