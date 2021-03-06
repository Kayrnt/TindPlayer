package fr.kayrnt.tindplayer.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.core.app.ActivityCompat;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.PositionAPIModel;

/**
 * Created by Kayrnt on 08/03/2014.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class PositionFragment extends MapBasedFragment {

    private static final int MAP_ZOOM = 17;
    private Marker marker;

    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static final int PERMISSION_REQUEST = 1337;

    public PositionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_position, container, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (permissions.length == 2 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    permissions[1] == Manifest.permission.ACCESS_COARSE_LOCATION &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setMyPosition();
            } else {
                Activity activity = getActivity();
                if (!activity.isFinishing()) {
                    Toast.makeText(activity, "We can't find your position to mark you", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onMapCreated(GoogleMap googleMap) {
//            LayoutInflater inflater = getActivity().getLayoutInflater();
        if (googleMap != null) {
            map = googleMap;

            //set map type
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission
                    .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(LOCATION_PERMS, PERMISSION_REQUEST);
            } else setMyPosition();


        }
    }

    public void setMyPosition() {

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getActivity().getSystemService
                (Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);
        // Get Current Location
        Location myLocation = null;

        if (provider != null) {

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            myLocation = locationManager.getLastKnownLocation(provider);

            if (myLocation != null) {

                // Get latitude of the current location
                double latitude = myLocation.getLatitude();

                // Get longitude of the current location
                double longitude = myLocation.getLongitude();

                // Create a LatLng object for the current location
                LatLng latLng = new LatLng(latitude, longitude);

                // Show the current location in Google Map and Zoom in the Google Map
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_ZOOM));

                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude,
                        longitude)).title("Your actual position !").
                        icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_indicator_current_position));

                map.addMarker(markerOptions);

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {

                        if (marker == null) {
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title
                                    ("Tinder thinks you're  here !");

                            marker = map.addMarker(markerOptions);
                        } else marker.setPosition(latLng);

                        marker.setPosition(latLng);
                        PositionAPIModel positionAPIModel = new PositionAPIModel();
                        positionAPIModel.setLat(latLng.latitude);
                        positionAPIModel.setLon(latLng.longitude);
                        TinderAPI.getInstance().updatePosition(getActivity(), positionAPIModel);
                    }
                });
            }
        }
    }
}
