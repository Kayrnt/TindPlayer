package fr.kayrnt.tindplayer.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import fr.kayrnt.tindplayer.client.TinderAPI;

/**
 * Created by Kayrnt on 05/01/15.
 */
public class FacebookAccounts {

    private static final String ACCOUNTS_KEY = "facebook_accounts";

    static FacebookAccounts instance;

    @Expose
    public LinkedList<FacebookAccount> accounts = new LinkedList<FacebookAccount>();

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    private static FacebookAccounts getFromJSON(String json) {
        return FacebookAccount.gson.fromJson(json, FacebookAccounts.class);
    }

    public String serialize() {
        return FacebookAccount.gson.toJson(this);
    }


    public static FacebookAccounts getInstance() {
        if (instance == null) {
            String str = TinderAPI.getInstance().mPrefs.getString(ACCOUNTS_KEY, null);
            if (str != null) {
                instance = getFromJSON(str);
            } else instance = new FacebookAccounts();
        }
        return instance;
    }

    public void saveWithCurrentAccount() {
        FacebookAccount currentAccount = TinderAPI.getInstance().account;
        boolean currentAccountAlreadyExists = false;
        if (currentAccount != null) {
        for(FacebookAccount account: accounts) {
                if(account.getId().equals(currentAccount.getId())){
                    account.setName(currentAccount.name);
                    account.setProfilePicture(currentAccount.profilePicture);
                    currentAccountAlreadyExists = true;
                }
            }
        }
        if(!currentAccountAlreadyExists) accounts.add(currentAccount);
        save();
    }

    public void logoutCurrentAccount() {
        FacebookAccount currentAccount = TinderAPI.getInstance().account;
        if(currentAccount != null) {
            FacebookAccount toRemove = null;
            for (FacebookAccount account : accounts) {
                if (account != null && currentAccount.getId().equals(account.getId()))
                    toRemove = account;
            }
            accounts.remove(toRemove);
        }
        save();
    }

    public void save() {
        TinderAPI.getInstance().mEditor.putString(ACCOUNTS_KEY, serialize());
        TinderAPI.getInstance().mEditor.apply();
    }


}
