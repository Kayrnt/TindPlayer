package fr.kayrnt.tindplayer.view;

import android.content.Context;
import android.util.AttributeSet;

import com.androidnetworking.widget.ANImageView;

/**
 * NetworkImageView with forced 1:1 aspect ratio.
 */
public class SquareNetworkImageView extends ANImageView {

    public SquareNetworkImageView(Context context) {
        super(context);
    }

    public SquareNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}