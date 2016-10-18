package fr.kayrnt.tindplayer.model;

import android.content.SharedPreferences;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

import fr.kayrnt.tindplayer.client.TinderAPI;

public class ProfileHistory {
    public final LinkedList<Profile> profiles = new LinkedList<Profile>();

    public static ProfileHistory create(String json) {
        return TinderAPI.getInstance().gson.fromJson(json, ProfileHistory.class);
    }

    public void serialize(String preferenceKey) {
        try {
            synchronized (profiles) {
                TinderAPI tinderAPI = TinderAPI.getInstance();
                final int slices = Math.min(profiles.size() / 50, 10);
                final Iterator<Profile> profileIterator = profiles.iterator();
                for (int i = 0; i <= slices; i++) {
                    int count = 0;
                    final ProfileHistory toSave = new ProfileHistory();
                    final SharedPreferences.Editor edit = tinderAPI.mPrefs.edit();
                    while (profileIterator.hasNext() && count <= 50) {
                        toSave.profiles.add(profileIterator.next());
                        count++;
                    }
                    final String sliceJson = tinderAPI.gson.toJson(toSave);
                    edit.putString(preferenceKey + "_" + i, sliceJson);
                    edit.apply();
                }
                final SharedPreferences.Editor edit = tinderAPI.mPrefs.edit();
                edit.putInt(preferenceKey + "_slices", slices);
                edit.apply();
            }
        } catch (ConcurrentModificationException e) {
            serialize(preferenceKey);
        }
    }
}