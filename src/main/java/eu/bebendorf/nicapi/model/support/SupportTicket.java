package eu.bebendorf.nicapi.model.support;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

public class SupportTicket implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String title;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public void answer(String message) throws NicAPIException {
        nicAPI.support().answerTicket(id, message);
    }
}
