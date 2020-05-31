package eu.bebendorf.nicapi.model.datacenter.vserver;

public enum ScheduledTaskAction {
    BACKUP("createBackup"),
    RESTART("restart"),
    START("start"),
    SHUTDOWN("shutdown");
    private String value;
    ScheduledTaskAction(String value){
        this.value = value;
    }
    public String toString() {
        return value;
    }
}
