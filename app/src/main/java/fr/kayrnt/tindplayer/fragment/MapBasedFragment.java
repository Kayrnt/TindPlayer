package fr.kayrnt.tindplayer.fragment;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.utils.AdvSupportMapFragment;

/**
 * Created by Kayrnt on 30/03/2014.
 */
public abstract class MapBasedFragment extends Fragment implements AdvSupportMapFragment
        .MySupportMapFragmentListener {

    protected GoogleMap map;
    protected AdvSupportMapFragment mapFragment;

    /*public void onDestroyView() {
        if (mapFragment != null) {
            try {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.remove(mapFragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map = null;
        super.onDestroyView();
        Log.i("=> Map based fragment", "destroy view");
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (AdvSupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = AdvSupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
            mapFragment.setListener(this);
        }
    }

}
