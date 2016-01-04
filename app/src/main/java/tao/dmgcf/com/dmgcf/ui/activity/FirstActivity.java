package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/4.
 */
public class FirstActivity extends  BaseActivity {
    private Button btn_finish;
    @Override
    protected void initView(Bundle savedInstanceState) {
    setContentView(R.layout.layout_first);
        btn_finish=getViewById(R.id.btn_finish);
    }

    @Override
    protected void setListener() {

        btn_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_finish){
            backward();
        }
    }
}
