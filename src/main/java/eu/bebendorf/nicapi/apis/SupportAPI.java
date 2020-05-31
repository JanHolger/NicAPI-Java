package eu.bebendorf.nicapi.apis;

import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.NicModel;
import eu.bebendorf.nicapi.model.support.SupportTicket;
import eu.bebendorf.nicapi.model.support.SupportTicketGroup;

import static eu.bebendorf.nicapi.HttpClient.map;

public class SupportAPI {

    private final NicAPI nicAPI;

    public SupportAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public SupportTicket[] getTickets() throws NicAPIException {
        return NicModel.set(nicAPI.get("support/tickets").dataOrError(TicketListResponse.class).tickets, nicAPI);
    }

    public SupportTicket getTicket(int id) throws NicAPIException {
        return NicModel.set(nicAPI.get("support/tickets/"+id).dataOrError(TicketSingleResponse.class).ticket, nicAPI);
    }

    public void answerTicket(int id, String message) throws NicAPIException {
        nicAPI.post("support/tickets/"+id+"/answer", map("message", message)).orError();
    }

    public SupportTicketGroup[] getTicketGroups() throws NicAPIException {
        return NicModel.set(nicAPI.get("support/tickets/groups").dataOrError(GroupListResponse.class).groups, nicAPI);
    }

    public SupportTicket createTicket(String title, String message, String email, SupportTicketGroup group) throws NicAPIException {
        return createTicket(title, message, email, group.id);
    }

    public SupportTicket createTicket(String title, String message, String email, int group) throws NicAPIException {
        return NicModel.set(nicAPI.post("support/tickets/create", map(
                "title", title,
                "message", message,
                "email", email,
                "ticket_group", group
        )).dataOrError(TicketSingleResponse.class).ticket, nicAPI);
    }

    private static class GroupListResponse {
        public SupportTicketGroup[] groups;
    }

    private static class TicketListResponse {
        public SupportTicket[] tickets;
    }

    private static class TicketSingleResponse {
        public SupportTicket ticket;
    }

}
