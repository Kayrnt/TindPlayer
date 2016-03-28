package fr.kayrnt.tindplayer.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import fr.kayrnt.tindplayer.client.TinderAPI;

/**
 * Created by Kayrnt on 02/01/15.
 */
public class FacebookAccount {

    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private static FacebookAccount getFromJSON(String json) {
        return new Gson().fromJson(json, FacebookAccount.class);
    }

    private String serialize() {
        return new Gson().toJson(this);
    }


    public static FacebookAccount getCurrentAccount() {
        String str = TinderAPI.getInstance().mPrefs.getString("current_facebook_account", null);
        if(str != null){
            return getFromJSON(str);
        } else  return null;
    }

    public void setCurrentAccount() {
        TinderAPI.getInstance().mEditor.putString("current_facebook_account", serialize());
        TinderAPI.getInstance().mEditor.apply();
    }

}
