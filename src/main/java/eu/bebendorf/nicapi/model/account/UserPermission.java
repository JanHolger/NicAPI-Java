package eu.bebendorf.nicapi.model.account;

import com.google.gson.annotations.SerializedName;

public class UserPermission {
    public String key;
    public String title;
    @SerializedName("descr")
    public String description;
    public Category category;
    public User.Type type;
    public enum Category {
        COLOCATION,
        ACCOUNT,
        ACCOUNTING,
        SUPPORT,
        MISC,
        SSL_CERTIFICATES,
        DOMAINS
    }
}
