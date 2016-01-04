package tao.dmgcf.com.dmgcf.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import tao.dmgcf.com.dmgcf.AppManager;

/**
 * Created by tao on 2016/1/4.
 */
public class ToastUtils {
    private ToastUtils() {
    }

    public static void  show(CharSequence text){
        if(text.length() < 10){
            Toast.makeText(AppManager.getInstance().currentActivity(),text,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(AppManager.getInstance().currentActivity(),text,Toast.LENGTH_LONG).show();
        }
    }


    public static void show(@StringRes int resId){
        show(AppManager.getInstance().currentActivity().getString(resId));
    }
}
