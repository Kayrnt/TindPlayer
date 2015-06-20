package fr.kayrnt.tindplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import fr.kayrnt.tindplayer.fragment.PhotoSlideFragment;
import fr.kayrnt.tindplayer.model.Photo;

public class PhotoAdapter extends FragmentPagerAdapter {

    private List<Photo> photos;

    public PhotoAdapter(FragmentManager manager, List<Photo> photos) {
        super(manager);
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Fragment getItem(int position){
        String url = this.photos.get(position).getProcessedFiles().get(0).getUrl();
        return PhotoSlideFragment.init(position, url);
    }

}
