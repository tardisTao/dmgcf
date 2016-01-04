package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tao.dmgcf.com.dmgcf.AppManager;
import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/4.
 */
public class MainActivity extends  BaseActivity {
    private Button btn;
    private Button btn_over;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        btn=getViewById(R.id.btn_main);
        btn_over=getViewById(R.id.btn_over);
    }

    @Override
    protected void setListener() {
        btn.setOnClickListener(this);
        btn_over.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_main){
           forward(FirstActivity.class);
        }else if(view.getId() == R.id.btn_over){
            AppManager.getInstance().AppExit(this);
        }
    }
}
