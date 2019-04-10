
package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("is_new_user")
    @Expose
    private boolean isNewUser;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("sms_login")
    @Expose
    private String smsLogin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data withId(String id) {
        this.id = id;
        return this;
    }

    public boolean isIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public Data withIsNewUser(boolean isNewUser) {
        this.isNewUser = isNewUser;
        return this;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Data withApiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Data withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getSmsLogin() {
        return smsLogin;
    }

    public void setSmsLogin(String smsLogin) {
        this.smsLogin = smsLogin;
    }

    public Data withSmsLogin(String smsLogin) {
        this.smsLogin = smsLogin;
        return this;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", isNewUser=" + isNewUser +
                ", apiToken='" + apiToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", smsLogin='" + smsLogin + '\'' +
                '}';
    }
}
