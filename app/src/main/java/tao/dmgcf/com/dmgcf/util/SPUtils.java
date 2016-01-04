package tao.dmgcf.com.dmgcf.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import tao.dmgcf.com.dmgcf.AppManager;

/**
 * Created by tao on 2016/1/4.
 */
public class SPUtils {
    private static SharedPreferences mSharedPreferences;

    private SPUtils() {
    }

    private static synchronized SharedPreferences getPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppManager.getInstance().currentActivity());
        }
        return mSharedPreferences;
    }


    public static void clear() {
        getPreferences().edit().clear().commit();
    }

    public static void putString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        return getPreferences().getString(key, null);
    }

    public static void putInt(String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    public static void putBoolean(String key, Boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public static void putLong(String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return getPreferences().getLong(key, 0);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static void remove(String key) {
        getPreferences().edit().remove(key).commit();
    }

    public static boolean hasKey(String key) {
        return getPreferences().contains(key);
    }


}
