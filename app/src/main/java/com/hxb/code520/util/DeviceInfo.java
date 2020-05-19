package com.hxb.code520.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * 类描述:手机设备的信息
 * 创建人:malin.myemail@gmail.com
 * 创建时间:15-11-10.下午3:26
 * 备注:
 * 参考项目:Android-Universal-Image-Loader
 */
public class DeviceInfo {
    private static volatile DeviceInfo instance;
    public static float mDensity;
    public static int mScreenHeightForPortrait;
    public static int mScreenWidthForPortrait;

    public static DeviceInfo getInstance() {
        if (instance == null) {
            synchronized (DeviceInfo.class) {
                if (instance == null) {
                    instance = new DeviceInfo();
                }
            }
        }
        return instance;
    }

    protected DeviceInfo() {
    }

    public void initializeScreenInfo(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mDensity = displayMetrics.density;
        mScreenWidthForPortrait = DataHelper.getIntergerSF(activity, "mScreenWidthForPortrait");
        mScreenHeightForPortrait = DataHelper.getIntergerSF(activity, "mScreenHeightForPortrait");
        if (mScreenHeightForPortrait == -1 || mScreenWidthForPortrait == -1) {
            if (displayMetrics.heightPixels >= displayMetrics.widthPixels) {
                mScreenWidthForPortrait = displayMetrics.widthPixels;
                mScreenHeightForPortrait = displayMetrics.heightPixels;
            } else {
                mScreenWidthForPortrait = displayMetrics.heightPixels;
                mScreenHeightForPortrait = displayMetrics.widthPixels;
            }
            DataHelper.setIntergerSF(activity, "mScreenWidthForPortrait", mScreenWidthForPortrait);
            DataHelper.setIntergerSF(activity, "mScreenHeightForPortrait", mScreenHeightForPortrait);
        }

    }
}
