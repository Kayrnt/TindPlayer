package fr.kayrnt.tindplayer.utils;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class AdvSupportMapFragment extends SupportMapFragment {

    private MySupportMapFragmentListener listener;

    public interface MySupportMapFragmentListener {
        public void onMapCreated(GoogleMap googleMap);
    }

    // value taken from source code
    private static final String MAP_OPTIONS = "MapOptions";

    public static AdvSupportMapFragment newInstance() {
        return new AdvSupportMapFragment();
    }

    public static AdvSupportMapFragment newInstance(GoogleMapOptions options) {
        AdvSupportMapFragment f = new AdvSupportMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(MAP_OPTIONS, options);
        f.setArguments(args);
        return f;
    }

// other newInstance methods ...


    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // here, as doc say, the map can be initialized, or the service is not available
        if (listener != null) {
            listener.onMapCreated(getMap());
        }

    }

    /**
     * @return the listener
     */
    public MySupportMapFragmentListener getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(MySupportMapFragmentListener listener) {
        this.listener = listener;
    }

}