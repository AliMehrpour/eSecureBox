// Copyright (c) 2015 Volcano. All rights reserved.
package com.volcano.esecurebox;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * A Custom application class
 */
public final class VlApplication extends Application {

    private static VlApplication sInstance;
    private static int sResumeCount = 0;

    public VlApplication() {
        super();
        sInstance = this;
    }

    public static VlApplication getInstance() {
        return sInstance;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static boolean isCurrentApp() {
        return sResumeCount > 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Managers.initialize();
        Managers.getApplicationLockManager().enableApplicationLockIfAvailable(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                sResumeCount++;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                sResumeCount--;
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
        });
    }
}

