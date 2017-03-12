package fr.kayrnt.tindplayer.api.match;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;
import fr.kayrnt.tindplayer.model.Match;
import fr.kayrnt.tindplayer.model.MatchesRequest;
import fr.kayrnt.tindplayer.model.Profile;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class MatchesFetchJSONListener extends BaseAPIErrorListener
        implements ParsedRequestListener<MatchesRequest> {
    private TinderAPI tinderAPI;
    private MatchedFragment fragment;

    public MatchesFetchJSONListener(TinderAPI tinderAPI, MatchedFragment fragment) {
        super(fragment.getContext());
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onResponse(MatchesRequest request) {
        Log.i("Matches API Listener", "updates API");
        List<Match> matches = request.getMatches();
        synchronized (tinderAPI.matchedProfiles.profiles) {
            tinderAPI.matchedProfiles.profiles.clear();
            for (Match match :
                    matches) {
                Profile profile = match.getPerson();
                if(profile != null) {
                    if (tinderAPI.matchedProfiles.profiles.size() >= 500)
                        tinderAPI.matchedProfiles.profiles.removeLast();
                    profile.liked = true;
                    tinderAPI.matchedProfiles.profiles.addFirst(profile);
                }
            }
            fragment.updateUI();
        }
    }

    @Override
    public void onError(ANError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        if ((error.getErrorCode() == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
            Toast.makeText(fragment.getContext(), "Authenticating...", Toast.LENGTH_SHORT).show();
        } else fallbackErrorResponse(error);
    }
}
