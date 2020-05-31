package eu.bebendorf.nicapi.apis;

import com.google.gson.JsonObject;
import eu.bebendorf.nicapi.HttpClient;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.account.APIToken;
import eu.bebendorf.nicapi.model.account.AccountConfigEntry;
import eu.bebendorf.nicapi.model.account.User;
import eu.bebendorf.nicapi.model.account.UserPermission;
import eu.bebendorf.nicapi.request.UserRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static eu.bebendorf.nicapi.HttpClient.map;

public class AccountAPI {

    private final NicAPI nicAPI;

    public AccountAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public User getAccount() throws NicAPIException {
        return NicModel.set(nicAPI.get("account/account").dataOrError(UserSingleResponse.class).user, nicAPI);
    }

    public User[] getUsers() throws NicAPIException {
        return NicModel.set(nicAPI.get("account/users").dataOrError(UserListResponse.class).users, nicAPI);
    }

    public User getUser(String username) throws NicAPIException {
        if(username.contains("/") || username.contains("?"))
            throw new RuntimeException("Invalid characters in parameter username!");
        return NicModel.set(nicAPI.get("account/users/"+username).dataOrError(UserSingleResponse.class).user, nicAPI);
    }

    public User createUser(UserRequest request) throws NicAPIException {
        return NicModel.set(nicAPI.post("account/users/create", request).dataOrError(UserSingleResponse.class).user, nicAPI);
    }

    public User updateUser(String username, UserRequest request) throws NicAPIException {
        if(username.contains("/") || username.contains("?"))
            throw new RuntimeException("Invalid characters in parameter username!");
        return NicModel.set(nicAPI.put("account/users/"+username+"/update", request).dataOrError(UserSingleResponse.class).user, nicAPI);
    }

    public void lockUser(String username) throws NicAPIException {
        if(username.contains("/") || username.contains("?"))
            throw new RuntimeException("Invalid characters in parameter username!");
        nicAPI.put("account/users/"+username+"/lock", null).orError();
    }

    public void unlockUser(String username) throws NicAPIException {
        if(username.contains("/") || username.contains("?"))
            throw new RuntimeException("Invalid characters in parameter username!");
        nicAPI.put("account/users/"+username+"/unlock", null).orError();
    }

    public UserPermission[] getPermissions(String username) throws NicAPIException {
        if(username.contains("/") || username.contains("?"))
            throw new RuntimeException("Invalid characters in parameter username!");
        return nicAPI.get("account/users/"+username+"/permissions", null).dataOrError(PermissionListResponse.class).permissions;
    }

    public APIToken createAPIToken(String user) throws NicAPIException {
        return createAPIToken(user, null);
    }

    public APIToken createAPIToken(String user, Date validUntil) throws NicAPIException {
        return NicModel.set(nicAPI.post("account/users/tokens/create", map("user", user, "valid_until", validUntil)).dataOrError(TokenSingleResponse.class).token, nicAPI);
    }

    public APIToken[] getAPITokens() throws NicAPIException {
        return NicModel.set(nicAPI.get("account/users/tokens/tokens").dataOrError(TokenListResponse.class).tokens, nicAPI);
    }

    public APIToken getAPIToken(String token) throws NicAPIException {
        if(token.contains("/") || token.contains("?"))
            throw new RuntimeException("Invalid characters in parameter token!");
        return NicModel.set(nicAPI.get("account/users/tokens/"+token).dataOrError(TokenSingleResponse.class).token, nicAPI);
    }

    public void deactivateAPIToken(String token) throws NicAPIException {
        if(token.contains("/") || token.contains("?"))
            throw new RuntimeException("Invalid characters in parameter token!");
        nicAPI.post("account/users/tokens/"+token+"/deactivate", null).orError();
    }

    public UserPermission[] getPermissions() throws NicAPIException {
        return nicAPI.get("/account/permissions/assigned").dataOrError(PermissionListResponse.class).permissions;
    }

    public UserPermission[] getAllPermissions() throws NicAPIException {
        return nicAPI.get("/account/permissions").dataOrError(PermissionListResponse.class).permissions;
    }

    public void setPermissions(String username, UserPermission... permissions) throws NicAPIException {
        String[] perms = new String[permissions.length];
        for(int i=0; i<perms.length; i++)
            perms[i] = permissions[i].key;
        setPermissions(username, perms);
    }

    public void setPermissions(String username, String... permissions) throws NicAPIException {
        nicAPI.put("/account/permissions/update", map("permissions", permissions)).orError();
    }

    public Map<String, String> getConfig() throws NicAPIException {
        JsonObject src = nicAPI.get("account/configs").dataOrError(JsonObject.class).getAsJsonObject("configs");
        Map<String, String> config = new HashMap<>();
        for(String key : src.keySet()){
            config.put(key, src.getAsJsonObject(key).get("value").getAsString());
        }
        return config;
    }

    public void setConfig(Map<String, String> config) throws NicAPIException {
        nicAPI.put("account/configs", config).orError();
    }

    private static class PermissionListResponse {
        public UserPermission[] permissions;
    }

    private static class UserSingleResponse {
        public User user;
    }

    private static class UserListResponse {
        public User[] users;
    }

    private static class TokenListResponse {
        public APIToken[] tokens;
    }

    private static class TokenSingleResponse {
        public APIToken token;
    }

}
