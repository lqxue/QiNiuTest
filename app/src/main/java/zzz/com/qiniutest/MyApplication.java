package zzz.com.qiniutest;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //
        mContext = getApplicationContext();
    }

    /**
     * 获取上下文
     */
    public static Context getContext() {
        return mContext;
    }

}
