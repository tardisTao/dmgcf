package tao.dmgcf.com.dmgcf.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tao.dmgcf.com.dmgcf.AppManager;
import tao.dmgcf.com.dmgcf.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected String TAG;
    private ProgressDialog mloadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        AppManager.getInstance().addActivity(this);
        initView(savedInstanceState);
        setListener();
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected void setListener() {
    }

    /**
     * 需要处理点击事件时，重写该方法
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }

    public void showLoadingDialog(@StringRes int resId) {
        showLoadingDialog(getString(resId));
    }

    public void showLoadingDialog(CharSequence message) {
        if (mloadingDialog == null) {
            mloadingDialog = new ProgressDialog(this);
            mloadingDialog.setCancelable(false);
        }
        mloadingDialog.setMessage(message);
        if (!mloadingDialog.isShowing()) {
            mloadingDialog.show();
        }

    }

    public void dismissLoadingDialog() {
        if (mloadingDialog != null && mloadingDialog.isShowing()) {
            mloadingDialog.dismiss();
        }
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 跳转到下一个Activity，并且销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forwardAndFinish(Class<?> cls) {
        startActivity(new Intent(this, cls));
        executeForwardAnim();
        finish();
    }

    /**
     * 跳转到下一个Activity，不销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forward(Class<?> cls) {
        startActivity(new Intent(this, cls));
        executeForwardAnim();
    }

    public void executeForwardAnim() {
        overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
    }

    /**
     * 回到上一个Activity，并销毁当前Activity
     */
    public void backward() {
        finish();
        executeBackwardAnim();

    }

    /**
     * 执行回到到上一个Activity的动画
     */
    public void executeBackwardAnim() {
        overridePendingTransition(R.anim.activity_backward_enter, R.anim.activity_backward_exit);
    }


}
