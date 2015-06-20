package fr.kayrnt.tindplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;

public class PhotoSlideFragment extends Fragment {
    String imageUrl = "";
    DisplayMetrics displayMetrics;
    public int width = 0;
    public NetworkImageView imageView;
    int position = 0;

    public static PhotoSlideFragment init(int position, String url) {
        PhotoSlideFragment fragment = new PhotoSlideFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("imageUrl", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.imageUrl = getArguments().getString("imageUrl");
            this.displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
            this.width = this.displayMetrics.widthPixels;
            this.position = getArguments().getInt("position");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_photo_slide, container, false);
        this.imageView = (NetworkImageView) root.findViewById(R.id.imageView);
        this.imageView.setImageUrl(imageUrl, MyApplication.getInstance().imageLoader());
        this.imageView.getLayoutParams().width = width;
//        this.imageView.setDefaultImageResId(R.drawable.pass);
        return root;
    }

}