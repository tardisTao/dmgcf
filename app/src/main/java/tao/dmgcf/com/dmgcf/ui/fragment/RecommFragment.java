package tao.dmgcf.com.dmgcf.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import in.srain.cube.views.ptr.PtrFrameLayout;
import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/5.
 */
public class RecommFragment extends Fragment {

    private PtrFrameLayout ptr_frame_recomm;
    private ViewPager viewpager_recomm;
    private BannerAdapter adapter;
    private ImageView[] mIndicators;

    private int mBannerPosition=0;
    private final int FAKE_BANNER_SIZE=400;
    private final int DEFAULT_BANNER_SIZE=4;
    private boolean mIsUserTouched=false;
    private Timer mTimer=new Timer();

    private int[] mImagesSrc={R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4};


    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            if (!mIsUserTouched){
                mBannerPosition++;

                if(!getActivity().isFinishing()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewpager_recomm.setCurrentItem(mBannerPosition);
                        }
                    });
                }

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_recomm_fragment, container, false);
        ptr_frame_recomm = (PtrFrameLayout) view.findViewById(R.id.ptr_frame_recomm);
        viewpager_recomm = (ViewPager) view.findViewById(R.id.viewpager_recomm);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter=new BannerAdapter(getActivity());
        viewpager_recomm.setAdapter(adapter);
        mTimer.schedule(timerTask,3000,3000);
    }




    class BannerAdapter extends PagerAdapter {
        private LayoutInflater mInflater;

        public BannerAdapter(Context context) {
            mInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return FAKE_BANNER_SIZE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position %=DEFAULT_BANNER_SIZE;
            View view=mInflater.inflate(R.layout.layout_banner_viewpager_item,container,false);
            ImageView imageView= (ImageView) view.findViewById(R.id.iv_banner_image_item);
            imageView.setImageResource(mImagesSrc[position]);
            final int pos=position;
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
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            int position=viewpager_recomm.getCurrentItem();
            if(position == 0){
                position=DEFAULT_BANNER_SIZE;
                viewpager_recomm.setCurrentItem(position,false);
            }else if(position == FAKE_BANNER_SIZE -1){
                viewpager_recomm.setCurrentItem(position,false);
            }
        }
    }
}
