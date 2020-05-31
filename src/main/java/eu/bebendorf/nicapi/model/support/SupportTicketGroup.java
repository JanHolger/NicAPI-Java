package eu.bebendorf.nicapi.model.support;

import com.google.gson.annotations.SerializedName;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;

import java.util.Date;

public class SupportTicketGroup implements NicModel {
    private transient NicAPI nicAPI;
    public int id;
    public String title;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public void setNicAPI(NicAPI nicAPI) {
        this.nicAPI = nicAPI;
    }
    public SupportTicket createTicket(String title, String message, String email) throws NicAPIException {
        return nicAPI.support().createTicket(title, message, email, id);
    }
}
