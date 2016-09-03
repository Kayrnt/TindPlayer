package fr.kayrnt.tindplayer.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import fr.kayrnt.tindplayer.client.TinderAPI;

/**
 * Created by Kayrnt on 02/01/15.
 */
public class FacebookAccount {

    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    public Long id;
    @Expose
    public String name;
    @Expose
    public String token;
    @Expose
    public String profilePicture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    private static FacebookAccount getFromJSON(String json) {
        return gson.fromJson(json, FacebookAccount.class);
    }


    private String serialize() {
        return gson.toJson(this);
    }

    public void setCurrentAccount() {
        Log.i("Facebook account", "Current account set to :" + name);
        TinderAPI.getInstance().mEditor.putString("current_facebook_account", serialize());
        TinderAPI.getInstance().mEditor.apply();
    }

    public ProfileDrawerItem getProfileDrawerItem() {
        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem()
                .withIdentifier(id)
                .withName(name)
                .withNameShown(true);
        if (profilePicture != null) profileDrawerItem.withIcon(profilePicture);
        return profileDrawerItem;
    }

    //public static

    public static FacebookAccount getCurrentAccount() {
        String str = TinderAPI.getInstance().mPrefs.getString("current_facebook_account", null);
        if (str != null) {
            return getFromJSON(str);
        } else return null;
    }


}
