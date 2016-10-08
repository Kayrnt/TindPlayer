package fr.kayrnt.tindplayer.model;

import com.google.gson.Gson;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class ProfileHistory {
    public final LinkedList<Profile> profiles = new LinkedList<Profile>();

    public static ProfileHistory create(String json) {
        return new Gson().fromJson(json, ProfileHistory.class);
    }

    public String serialize() {
        try {
            synchronized (profiles) {
                ProfileHistory toSave = new ProfileHistory();
                int count = 0;
                Iterator<Profile> profileIterator = profiles.iterator();
                while(profileIterator.hasNext() && !(count >= 300)){
                    toSave.profiles.add(profileIterator.next());
                    count++;
                }
                return new Gson().toJson(this);
            }
        } catch (ConcurrentModificationException e) {
            return serialize();
        }
    }
}