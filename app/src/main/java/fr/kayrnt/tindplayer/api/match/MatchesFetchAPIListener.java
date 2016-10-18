package fr.kayrnt.tindplayer.api.match;

import android.util.Log;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;
import fr.kayrnt.tindplayer.model.Match;
import fr.kayrnt.tindplayer.model.MatchesRequest;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.ProfileRequest;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class MatchesFetchAPIListener
        implements Response.Listener<MatchesRequest> {
    private TinderAPI client;
    private MatchedFragment fragment;

    public MatchesFetchAPIListener(TinderAPI tinderAPI, MatchedFragment fragment) {
        this.client = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onResponse(MatchesRequest request) {
        Log.i("Matches API Listener", "updates API");
        List<Match> matches = request.getMatches();
        synchronized (client.matchedProfiles.profiles) {
            client.matchedProfiles.profiles.clear();
            for (Match match :
                    matches) {
                Profile profile = match.getPerson();
                if(profile != null) {
                    if (client.matchedProfiles.profiles.size() >= 500)
                        client.matchedProfiles.profiles.removeLast();
                    profile.liked = true;
                    client.matchedProfiles.profiles.addFirst(profile);
                }
            }
            fragment.updateUI();
        }
    }
}
