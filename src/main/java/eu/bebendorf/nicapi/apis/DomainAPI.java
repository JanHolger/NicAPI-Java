package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.*;
import eu.bebendorf.nicapi.model.domain.DomainTLD;
import eu.bebendorf.nicapi.model.domain.CountryCode;
import eu.bebendorf.nicapi.model.domain.Domain;
import eu.bebendorf.nicapi.model.domain.DomainHandle;
import eu.bebendorf.nicapi.model.domain.Nameserver;
import eu.bebendorf.nicapi.request.DomainRequest;

import java.util.Date;

import static eu.bebendorf.nicapi.HttpClient.map;

public class DomainAPI {

    private final NicAPI nicAPI;

    public DomainAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public Domain[] getDomains() throws NicAPIException {
        return NicModel.set(nicAPI.get("domain/domains").dataOrError(DomainListResponse.class).domains, nicAPI);
    }

    public Domain getDomain(String name) throws NicAPIException {
        return NicModel.set(nicAPI.get("domain/domains/show", map("domainName", name)).dataOrError(DomainSingleResponse.class).domain, nicAPI);
    }

    public Domain createDomain(DomainRequest request) throws NicAPIException {
        return NicModel.set(nicAPI.post("domain/domains/create", request).dataOrError(DomainSingleResponse.class).domain, nicAPI);
    }

    public void restoreDomain(String name) throws NicAPIException {
        nicAPI.post("domain/domains/restore", map("domainName", name)).orError();
    }

    public String createAuthCode(String name) throws NicAPIException {
        return nicAPI.post("domain/domains/authcode", map("domainName", name)).dataOrError(DomainSingleResponse.class).domain.authCode;
    }

    public void updateDomain(DomainRequest request) throws NicAPIException {
        nicAPI.post("domain/domains/edit", request).orError();
    }

    public boolean checkDomain(String name) throws NicAPIException {
        return nicAPI.post("domain/domains/check", map("domainName", name)).dataOrError(DomainCheckResponse.class).available;
    }

    public void deleteDomain(String name) throws NicAPIException {
        nicAPI.delete("domain/domains/delete", map("domainName", name)).orError();
    }

    public void deleteDomain(String name, Date date) throws NicAPIException {
        nicAPI.delete("domain/domains/delete", map("domainName", name, "date", date)).orError();
    }

    public void undeleteDomain(String name) throws NicAPIException {
        nicAPI.post("domain/domains/undelete", map("domainName", name)).orError();
    }

    public DomainTLD[] getTLDs() throws NicAPIException {
        return nicAPI.get("domain/domains/tlds").dataOrError(TLDListResponse.class).tlds;
    }

    public DomainHandle[] getHandles() throws NicAPIException {
       return NicModel.set(nicAPI.get("/domain/handles").dataOrError(HandleListResponse.class).handles, nicAPI);
    }

    public DomainHandle getHandle(String handle) throws NicAPIException {
        return NicModel.set(nicAPI.get("/domain/handles/show", map("handle", handle)).dataOrError(HandleSingleResponse.class).handle, nicAPI);
    }

    public void deleteHandle(String handle) throws NicAPIException {
        nicAPI.delete("/domain/handles/delete", map("handle", handle)).orError();
    }

    public DomainHandle createHandle(DomainHandle handle) throws NicAPIException {
        return NicModel.set(nicAPI.post("/domain/handles/create", handle).dataOrError(HandleSingleResponse.class).handle, nicAPI);
    }

    public void updateHandle(DomainHandle handle) throws NicAPIException {
        nicAPI.post("/domain/handles/edit", handle).orError();
    }

    public CountryCode[] getCountries() throws NicAPIException {
        return nicAPI.get("/domain/handles/countries").dataOrError(CountryListResponse.class).countries;
    }

    public Nameserver[] getNameservers() throws NicAPIException {
        return NicModel.set(nicAPI.get("domain/nameservers").dataOrError(NameserverListResponse.class).nameservers, nicAPI);
    }

    public Nameserver getNameserver(String nameserver) throws NicAPIException {
        return NicModel.set(nicAPI.get("domain/nameservers/show", map("nameserver", nameserver)).dataOrError(NameserverSingleResponse.class).nameserver, nicAPI);
    }

    public void refreshNameserver(String nameserver) throws NicAPIException {
        nicAPI.post("domain/nameservers/refresh", map("nameserver", nameserver)).orError();
    }

    public void deleteNameserver(String nameserver) throws NicAPIException {
        nicAPI.delete("domain/nameservers/delete", map("nameserver", nameserver)).orError();
    }

    private static class NameserverListResponse {
        public Nameserver[] nameservers;
    }

    private static class NameserverSingleResponse {
        public Nameserver nameserver;
    }

    private static class HandleListResponse {
        public DomainHandle[] handles;
    }

    private static class HandleSingleResponse {
        public DomainHandle handle;
    }

    private static class DomainListResponse {
        public Domain[] domains;
    }

    private static class DomainSingleResponse {
        public Domain domain;
    }

    private static class DomainCheckResponse {
        public boolean available;
    }

    private static class TLDListResponse {
        public DomainTLD[] tlds;
    }

    private static class CountryListResponse {
        public CountryCode[] countries;
    }

}
