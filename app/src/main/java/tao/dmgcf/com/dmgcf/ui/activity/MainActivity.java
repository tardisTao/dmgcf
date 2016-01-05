package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

import tao.dmgcf.com.dmgcf.R;
import tao.dmgcf.com.dmgcf.ui.fragment.MoreFragment;
import tao.dmgcf.com.dmgcf.ui.fragment.MypropertyFragment;
import tao.dmgcf.com.dmgcf.ui.fragment.ProductFragment;
import tao.dmgcf.com.dmgcf.ui.fragment.RecommFragment;

/**
 * Created by tao on 2016/1/4.
 */
public class MainActivity extends BaseActivity {

    private static int currSel=0;
    private RadioGroup group;

    private Fragment recommFm=new RecommFragment();
    private Fragment productFm=new ProductFragment();
    private Fragment mypropertyFm=new MypropertyFragment();
    private Fragment moreFm=new MoreFragment();
    private List<Fragment> fragmentList= Arrays.asList(recommFm,productFm,mypropertyFm,moreFm);

    private FragmentManager fragmentManager;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_main);
        fragmentManager=getSupportFragmentManager();
        group=getViewById(R.id.group_public_footer);

        initFootBar();
    }

    private void initFootBar() {

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_foot_bar_recomm:
                        currSel=0;
                        break;
                    case R.id.rb_foot_bar_product:
                        currSel=1;
                        break;
                    case R.id.rb_foot_bar_myproperty:
                        currSel=2;
                        break;
                    case R.id.rb_foot_bar_more:
                        currSel=3;
                        break;
                }
                addFragmentToStack(currSel);
                if(currSel == 2){
                    forward(FirstActivity.class);
                }
            }
        });
        addFragmentToStack(0);
    }


    private void addFragmentToStack(int cur){
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment=fragmentList.get(cur);
        if(!fragment.isAdded()){
            fragmentTransaction.add(R.id.fragment_main_container,fragment);
        }
        for (int i=0;i < fragmentList.size();i++){
            Fragment f=fragmentList.get(i);
            if(i == cur && f.isAdded()){
                fragmentTransaction.show(f);
            }else if(f != null &&f.isAdded() && f.isVisible()){
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

}
