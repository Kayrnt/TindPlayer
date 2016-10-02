package fr.kayrnt.tindplayer.model;

import com.google.gson.Gson;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class ProfileHistory {
    public final LinkedList<Profile> profiles = new LinkedList<Profile>();

    public static ProfileHistory create(String json) {
        return new Gson().fromJson(json, ProfileHistory.class);
    }

    public String serialize() {
        try {
            synchronized (profiles) {
                return new Gson().toJson(this);
            }
        } catch (ConcurrentModificationException e) {
            return serialize();
        }
    }
}