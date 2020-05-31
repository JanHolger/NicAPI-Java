package eu.bebendorf.nicapi;

import eu.bebendorf.nicapi.apis.*;
import eu.bebendorf.nicapi.response.NicAPIResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NicAPI extends HttpClient {

    private static final String BASE_URL = "https://connect.nicapi.eu/api/v1";

    private final String authToken;

    private final DatacenterAPI datacenter;
    private final DomainAPI domain;
    private final DNSAPI dns;
    private final AccountingAPI accounting;
    private final SupportAPI support;
    private final AccountAPI account;
    private final MiscAPI misc;
    private final SSLAPI ssl;

    public NicAPI(String apiToken){
        this.authToken = apiToken;
        datacenter = new DatacenterAPI(this);
        domain = new DomainAPI(this);
        dns = new DNSAPI(this);
        accounting = new AccountingAPI(this);
        support = new SupportAPI(this);
        account = new AccountAPI(this);
        misc = new MiscAPI(this);
        ssl = new SSLAPI(this);
    }

    public byte[] httpRequest(String method, String url, Map<String, String> header, Map<String, Object> query, byte[] body) {
        if(!url.startsWith("/"))
            url = "/"+url;
        query.put("authToken", authToken);
        System.out.println(body);
        return super.httpRequest(method, BASE_URL + url, header, query, body);
    }

    public NicAPIResponse request(String method, String path, Map<String, Object> query, Object body){
        byte[] res = httpRequest(method, path, null, query, method.equalsIgnoreCase("get")?null:makeForm(body).getBytes(StandardCharsets.UTF_8));
        try {
            return GSON.fromJson(new String(res, StandardCharsets.UTF_8), NicAPIResponse.class);
        }catch (Exception exception){
            throw new RuntimeException("Unparsable Response:\n"+res);
        }
    }

    public DatacenterAPI datacenter(){
        return datacenter;
    }

    public DomainAPI domain(){
        return domain;
    }

    public DNSAPI dns(){
        return dns;
    }

    public AccountingAPI accounting(){
        return accounting;
    }

    public SupportAPI support(){
        return support;
    }

    public AccountAPI account(){
        return account;
    }

    public MiscAPI misc(){
        return misc;
    }

    public SSLAPI ssl(){
        return ssl;
    }

    public NicAPIResponse get(String path){
        return request("GET", path, new HashMap<>(), null);
    }

    public NicAPIResponse get(String path, Map<String, Object> query){
        return request("GET", path, query, null);
    }

    public NicAPIResponse post(String path, Object body){
        return request("POST", path, new HashMap<>(), body);
    }

    public NicAPIResponse put(String path, Object body){
        return request("PUT", path, new HashMap<>(), body);
    }

    public NicAPIResponse delete(String path, Object body){
        return request("DELETE", path, new HashMap<>(), body);
    }

    public NicAPIResponse delete(String path){
        return delete(path, null);
    }

}
