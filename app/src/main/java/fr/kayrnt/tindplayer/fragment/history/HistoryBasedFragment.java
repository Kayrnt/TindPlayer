package fr.kayrnt.tindplayer.fragment.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.activity.ProfileDetailActivity;
import fr.kayrnt.tindplayer.adapter.ProfileAdapter;
import fr.kayrnt.tindplayer.model.ProfileHistory;

public abstract class HistoryBasedFragment extends Fragment implements AdapterView
        .OnItemClickListener {
    ProfileAdapter profileAdapter;
    GridView gridView;

    abstract String getType();

    abstract ProfileHistory getHistory();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    protected abstract void refreshItems();

    protected void update() {
        refreshItems();
    }

    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }
        update();
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.fragment_history_list, null);
        this.gridView = ((GridView) localView.findViewById(R.id.historyGridView));
        profileAdapter = new ProfileAdapter(getActivity(), getHistory().profiles, true);
        this.gridView.setAdapter(profileAdapter);
        this.gridView.setOnItemClickListener(this);
        return localView;
    }

    @Override
    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int location, long paramLong) {
        try {
            String str = getHistory().profiles.get(location).getId();
            Intent localIntent = new Intent(this.getActivity(), ProfileDetailActivity.class);
            localIntent.putExtra("item_id", str);
            localIntent.putExtra("profile_type", getType());
            this.startActivity(localIntent);
        } catch(ArrayIndexOutOfBoundsException e) {
            Toast.makeText(getActivity(), "Corrupted history, please restart the app", Toast
                    .LENGTH_SHORT).show();
        }
    }



}