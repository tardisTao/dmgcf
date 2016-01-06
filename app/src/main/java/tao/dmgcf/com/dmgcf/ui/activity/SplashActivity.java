package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import tao.dmgcf.com.dmgcf.R;
import tao.dmgcf.com.dmgcf.util.SPUtils;

/**
 * Created by tao on 2016/1/4.
 */
public class SplashActivity extends BaseActivity {
    private boolean is_first;
    public static  final String IS_FIRST_APP="is_first_app";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_splash);
        initData();
    }

    private void initData() {
        is_first = SPUtils.getBoolean(IS_FIRST_APP,true);
        if(is_first){
            SPUtils.putBoolean(IS_FIRST_APP,false);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    forwardAndFinish(GuideActivity.class);
                }
            },2000);

        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    forwardAndFinish(MainActivity.class);
                }
            },2000);
        }
    }

}
