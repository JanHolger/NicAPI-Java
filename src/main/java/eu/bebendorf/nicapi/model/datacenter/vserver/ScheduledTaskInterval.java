package eu.bebendorf.nicapi.model.datacenter.vserver;

public enum ScheduledTaskInterval {
    HOURLY("hour"),
    DAYLY("day"),
    WEEKLY("week"),
    MONTHLY("month"),
    YEAR("year");
    private String value;
    ScheduledTaskInterval(String value){
        this.value = value;
    }
    public String toString() {
        return value;
    }
}
