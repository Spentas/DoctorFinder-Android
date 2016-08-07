package com.spentas.javad.doctorfinder.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.spentas.javad.doctorfinder.util.Logger;

public class App extends MultiDexApplication {

    private AppComponent mComponent;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        mComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        onActivityLifecycleCallback();
        mComponent.inject(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(App.this);
    }
    public static Context getContext(){
        return mContext;
    }


    public AppComponent getComponent(){
        return mComponent;
    }

    private void onActivityLifecycleCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logger.i("App Started");

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.i("App Resumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.i("App Paused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logger.i("App Stopped");

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Logger.i("App SaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.i("App Destroyed");
            }

        });
    }

}
