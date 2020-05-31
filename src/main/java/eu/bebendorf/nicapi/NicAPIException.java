package eu.bebendorf.nicapi;

import eu.bebendorf.nicapi.response.NicAPIResponse;

public class NicAPIException extends Exception {
    private final NicAPIResponse response;
    public NicAPIException(NicAPIResponse response){
        this.response = response;
    }
    public NicAPIResponse getResponse(){
        return response;
    }
    public String getMessage(){
        if(response.messages.errors.length > 0)
            return response.messages.errors[0].message;
        return "No error message provided!";
    }
}
