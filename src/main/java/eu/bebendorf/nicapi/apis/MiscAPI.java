package eu.bebendorf.nicapi.apis;

import com.google.gson.JsonObject;
import eu.bebendorf.nicapi.NicAPI;
import eu.bebendorf.nicapi.NicAPIException;
import eu.bebendorf.nicapi.model.misc.TeamspeakInstance;
import eu.bebendorf.nicapi.model.misc.TeamspeakInstanceGraphEntry;

public class MiscAPI {

    private final NicAPI nicAPI;

    public MiscAPI(NicAPI nicAPI){
        this.nicAPI = nicAPI;
    }

    public TeamspeakInstance[] getTeamspeakInstances() throws NicAPIException {
        return this.nicAPI.get("misc/teamspeak-instances").dataOrError(TeamspeakInstanceListResponse.class).instances;
    }

    public TeamspeakInstance getTeamspeakInstance(int id) throws NicAPIException {
        return this.nicAPI.get("misc/teamspeak-instances/"+id).dataOrError(TeamspeakInstanceSingleResponse.class).instance;
    }

    public TeamspeakInstanceGraphEntry[] getTeamspeakInstanceGraph(int id) throws NicAPIException {
        return this.nicAPI.get("misc/teamspeak-instances/"+id).dataOrError(TeamspeakInstanceSingleResponse.class).graph();
    }

    private static class TeamspeakInstanceListResponse {
        public TeamspeakInstance[] instances;
    }

    private static class TeamspeakInstanceSingleResponse {
        public TeamspeakInstance instance;
        private JsonObject graph;
        public TeamspeakInstanceGraphEntry[] graph(){
            JsonObject graphData = graph.getAsJsonObject("playerHistory").getAsJsonObject("data");
            TeamspeakInstanceGraphEntry[] entries = new TeamspeakInstanceGraphEntry[graphData.getAsJsonObject("current").size()];
            int i = 0;
            for(String key : graphData.getAsJsonObject("current").keySet()){
                entries[i] = new TeamspeakInstanceGraphEntry();
                entries[i].time = Long.valueOf(key);
                entries[i].clients = graphData.getAsJsonObject("current").get(key).getAsInt();
                entries[i].maxClients = graphData.getAsJsonObject("maximum").get(key).getAsInt();
                i++;
            }
            return entries;
        }
    }



}
