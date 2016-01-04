package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/4.
 */
public class GuideActivity extends BaseActivity {
    private Button btn_guide;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_guide);
        btn_guide = getViewById(R.id.btn_guide);
    }

    @Override
    protected void setListener() {
        btn_guide.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guide) {
            forwardAndFinish(MainActivity.class);
        }
    }
}
