package eu.bebendorf.nicapi.model;

import eu.bebendorf.nicapi.NicAPI;

public interface NicModel {
    void setNicAPI(NicAPI nicAPI);
    static <T extends NicModel> T[] set(T[] array, NicAPI nicAPI){
        for(T e : array)
            e.setNicAPI(nicAPI);
        return array;
    }
    static <T extends NicModel> T set(T e, NicAPI nicAPI){
        e.setNicAPI(nicAPI);
        return e;
    }
}
