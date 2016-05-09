package fr.kayrnt.tindplayer.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.view.SquareNetworkImageView;

public class ProfileAdapter extends ArrayAdapter<Profile> {

    public class ProfileView {
        SquareNetworkImageView squareNetworkImageView;
        TextView name;
        public ImageView passIcon;
        public View passScreen;
        public Profile profile;
    }

    private Context context;
    private final boolean history;
    private List<Profile> items;
    private Handler mHandler = new Handler();


    public ProfileAdapter(Context context, List<Profile> list, boolean history) {
        super(context, 0, list);
        items = new ArrayList<>(list);
        this.context = context;
        this.history = history;
    }

    public void updateWith(final List<Profile> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                items = new ArrayList<>(list);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final Profile profile = getItem(position);
            LayoutInflater localLayoutInflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            ProfileView profileView;
            if (convertView == null) {
                convertView = localLayoutInflater.inflate(R.layout.profile_list_item_grid, null);
                profileView = new ProfileView();
                profileView.name = ((TextView) convertView.findViewById(R.id.nameAndAge_grid));
                profileView.squareNetworkImageView = ((SquareNetworkImageView) convertView
                        .findViewById(R.id.profile_thumb_grid));
                profileView.passScreen = convertView.findViewById(R.id.passScreen);
                profileView.passIcon = ((ImageView) convertView.findViewById(R.id.passIcon));
                convertView.setTag(profileView);

            } else profileView = (ProfileView) convertView.getTag();

            profileView.profile = profile;
            profileView.name.setText(profile.getNameAndAge());
            if ((profile.getPhotos() != null) && (!profile.getPhotos().isEmpty()) && (!profile.getPhotos()
                    .get(0)
                    .getProcessedFiles()
                    .isEmpty()))
                profileView.squareNetworkImageView.setImageUrl(profile.getPhotos().get(0)
                        .getProcessedFiles().get(2).getUrl(), MyApplication.getInstance().imageLoader());

//        profileView.passScreen.setVisibility(View.GONE);
//        profileView.passIcon.setVisibility(View.GONE);
            if (profile.shouldLike || history) {
                profileView.passScreen.setVisibility(View.GONE);
                profileView.passIcon.setVisibility(View.GONE);
            } else {
                profileView.passScreen.setVisibility(View.VISIBLE);
                profileView.passIcon.setVisibility(View.VISIBLE);
            }
        } catch (IndexOutOfBoundsException e) {
            Log.w("profile adapter", "list not expected to be empty at position "+position);
        }

        return convertView;

    }
}