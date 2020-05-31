package eu.bebendorf.nicapi.response;

import com.google.gson.JsonElement;
import eu.bebendorf.nicapi.HttpClient;
import eu.bebendorf.nicapi.NicAPIException;

public class NicAPIResponse {

    public Metadata metadata;
    public String status;
    public Messages messages;
    public JsonElement data;

    public <T> T data(Class<T> clazz){
        if(data == null)
            return null;
        return HttpClient.GSON.fromJson(data, clazz);
    }

    public <T> T dataOrError(Class<T> clazz) throws NicAPIException {
        orError();
        return data(clazz);
    }

    public void orError() throws NicAPIException {
        if(!isSuccess())
            throw new NicAPIException(this);
    }

    public boolean isSuccess(){
        return status.equals("success");
    }

    public static class Messages {
        public Message[] warnings;
        public Message[] errors;
        public Message[] success;
        public static class Message {
            public int code;
            public String message;
        }
    }

    public static class Metadata {
        public String clientTransactionId;
        public String serverTransactionId;
    }

}
