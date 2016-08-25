package fr.kayrnt.tindplayer.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.LinkedList;

import fr.kayrnt.tindplayer.client.TinderAPI;

/**
 * Created by Kayrnt on 05/01/15.
 */
public class FacebookAccounts {

    private static final String ACCOUNTS_KEY = "facebook_accounts";

    @Expose
    public LinkedList<FacebookAccount> accounts = new LinkedList<FacebookAccount>();

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    public static FacebookAccounts getFromJSON(String json) {
        return FacebookAccount.gson.fromJson(json, FacebookAccounts.class);
    }

    public String serialize() {
        return FacebookAccount.gson.toJson(this);
    }


    public static FacebookAccounts getAccounts() {
        String str = TinderAPI.getInstance().mPrefs.getString(ACCOUNTS_KEY, null);
        if(str != null){
            return getFromJSON(str);
        } else  return new FacebookAccounts();
    }

    public void save() {
        TinderAPI.getInstance().mEditor.putString(ACCOUNTS_KEY, serialize());
        TinderAPI.getInstance().mEditor.apply();
    }


}
