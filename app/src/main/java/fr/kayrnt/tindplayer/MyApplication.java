package fr.kayrnt.tindplayer;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import fr.kayrnt.tindplayer.utils.SessionManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static SessionManager sessionManager;
    private static SharedPreferences preferences;
    private RequestQueue requestQueue;
    private ImageLoader mImageLoader;

    public static MyApplication getInstance() {
        if (myApplication == null)
            myApplication = new MyApplication();
        return myApplication;
    }

    public static SessionManager session() {
        if (sessionManager == null)
            sessionManager = new SessionManager(myApplication);
        return sessionManager;
    }

    public static SharedPreferences getSharedPreferences() {
        if (preferences == null)
            preferences = myApplication.getSharedPreferences("AndroidPref", MODE_PRIVATE);
        return preferences;
    }

    public <T> void withSessionManager(Request<T> request) {
        Log.i("HTTP API Request", "url : "+request.getUrl());
//        request.setTag(sessionManager);
        queue().add(request);
    }

    public RequestQueue queue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this);
        }
        return this.requestQueue;
    }

    public ImageLoader imageLoader() {
        if(mImageLoader == null)
        mImageLoader = new ImageLoader(queue(), new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        return mImageLoader;
    }
//    public sh f() {
//        queue();
//        if (this.queue == null)
//            this.queue = new sh(this.requestQueue, new LruBitmapCache());
//        return this.queue;
//    }

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        myApplication = this;
        preferences = getSharedPreferences("AndroidPref", MODE_PRIVATE);
        sessionManager = new SessionManager(this);
    }
}
