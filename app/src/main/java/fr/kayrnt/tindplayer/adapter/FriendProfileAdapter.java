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
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.model.FriendProfile;
import fr.kayrnt.tindplayer.model.FriendProfile;
import fr.kayrnt.tindplayer.view.SquareNetworkImageView;

public class FriendProfileAdapter extends ArrayAdapter<FriendProfile> {

    public class ProfileView {
        SquareNetworkImageView squareNetworkImageView;
        TextView name;
        public ImageView passIcon;
        public View passScreen;
        public FriendProfile friendProfile;
    }

    private Context context;
    private Handler mHandler = new Handler();


    public FriendProfileAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    public void updateWith(final List<FriendProfile> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                FriendProfileAdapter.this.clear();
                FriendProfileAdapter.this.addAll(list);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        try {
            final FriendProfile friendProfile = getItem(position);
            profileView.friendProfile = friendProfile;
            profileView.name.setText(friendProfile.getName());
            if ((friendProfile.getPhoto() != null) && (!friendProfile.getPhoto().isEmpty()) &&
                    (!friendProfile.getPhoto()
                    .get(0)
                    .getProcessedFiles()
                    .isEmpty()))
                profileView.squareNetworkImageView.setImageUrl(friendProfile.getPhoto().get(0)
                        .getProcessedFiles().get(2).getUrl());

                profileView.passScreen.setVisibility(View.GONE);
                profileView.passIcon.setVisibility(View.GONE);
        } catch (IndexOutOfBoundsException e) {
            Log.w("FriendProfile adapter", "list not expected to be empty at position "+position);
        }

        return convertView;

    }
}