package tao.dmgcf.com.dmgcf.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/5.
 */
public class RecommFragment extends Fragment {
    private static final String TAG = "RecommFragment";

    private PtrFrameLayout ptr_frame_recomm;
    private ViewPager viewpager_recomm;
    private LinearLayout ll_dot_group_recomm;
    private BannerAdapter adapter;
    /**
     * ViewPager中ImageView的容器
     */
    private List<ImageView> imageViewContainer = null;
    /**
     * 上一个被选中的小圆点的索引，默认值为0
     */
    private int preDotPosition = 0;
    /**
     * Banner滚动线程是否销毁的标志，默认不销毁
     */
    private boolean isStop = false;
    /**
     * Banner的切换下一个page的间隔时间
     */
    private long scrollTimeOffset = 3000;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_recomm_fragment, container, false);
        ptr_frame_recomm = (PtrFrameLayout) view.findViewById(R.id.ptr_frame_recomm);
        viewpager_recomm = (ViewPager) view.findViewById(R.id.viewpager_recomm);
        ll_dot_group_recomm = (LinearLayout) view.findViewById(R.id.ll_dot_group_recomm);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageViewContainer = new ArrayList<ImageView>();

        int[] imageIds = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
        ImageView imageView = null;
        View dot = null;
        LinearLayout.LayoutParams params = null;
        for (int id : imageIds) {
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(id);
            imageViewContainer.add(imageView);

            // 每循环一次添加一个点到线行布局中
            dot = new View(getActivity());
            dot.setBackgroundResource(R.drawable.dot_bg_selector);
            params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 30;
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            ll_dot_group_recomm.addView(dot);
        }

        adapter = new BannerAdapter();
        viewpager_recomm.setAdapter(adapter);
        viewpager_recomm.addOnPageChangeListener(new BannerPageChangeListener());
        ll_dot_group_recomm.getChildAt(0).setEnabled(true);
        viewpager_recomm.setCurrentItem(0);

        startBannerScrollThread();

    }

    @Override
    public void onDestroy() {
        isStop = true;
        super.onDestroy();
    }

    private void startBannerScrollThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    //每间隔三秒钟发一条消息到主线程，更新viewpager界面
                    SystemClock.sleep(scrollTimeOffset);
                    try {
                        if (!getActivity().isFinishing()) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int newindex = viewpager_recomm.getCurrentItem() + 1;
                                    viewpager_recomm.setCurrentItem(newindex);
                                }
                            });
                        }

                    } catch (NullPointerException e) {
                        Log.e(TAG, "getActivity为空");
                    }
                }
            }
        }).start();
    }


    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageViewContainer.get(position % imageViewContainer.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewContainer.get(position % imageViewContainer.size()));
        }

    }


    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 取余后的索引，得到新的page的索引
            int newPositon = position % imageViewContainer.size();
            // 把上一个点设置为被选中
            ll_dot_group_recomm.getChildAt(preDotPosition).setEnabled(false);
            // 根据索引设置那个点被选中
            ll_dot_group_recomm.getChildAt(newPositon).setEnabled(true);
            // 新索引赋值给上一个索引的位置
            preDotPosition = newPositon;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
