package fr.kayrnt.tindplayer.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class TabListener<T extends Fragment>
        implements ActionBar.TabListener {
    private final Activity mActivity;
    private final Class<T> mClass;
    private Fragment mFragment;
    private final String mTag;

    public TabListener(Activity activity, String tag, Class<T> clazz) {
        this.mActivity = activity;
        this.mTag = tag;
        this.mClass = clazz;
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction paramFragmentTransaction) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction transaction) {
        if (this.mFragment == null) {
            this.mFragment = Fragment.instantiate(this.mActivity, this.mClass.getName());
            transaction.add(this.mFragment, this.mTag);
            return;
        }
        transaction.attach(this.mFragment);
    }

    public void onTabUnselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction) {
        if (this.mFragment != null)
            paramFragmentTransaction.detach(this.mFragment);
    }
}