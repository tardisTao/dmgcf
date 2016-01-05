package tao.dmgcf.com.dmgcf.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import com.viewpagerindicator.CirclePageIndicator;
import tao.dmgcf.com.dmgcf.R;

/**
 * Created by tao on 2016/1/4.
 */
public class GuideActivity extends BaseActivity {
    private Button btn_guide_in;
    private CirclePageIndicator indicator;
    private ViewPager viewpager_guide_page;
    private GalleryPagerAdapter adapter;
    private int[] images = {R.mipmap.newer01, R.mipmap.newer02, R.mipmap.newer03, R.mipmap.newer04};


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_guide);
        btn_guide_in = getViewById(R.id.btn_guide_in);
        viewpager_guide_page = getViewById(R.id.viewpager_guide_page);
        indicator = getViewById(R.id.indicator_guide_page);

        indicator.setVisibility(View.VISIBLE);
        viewpager_guide_page.setVisibility(View.VISIBLE);

        initGuideGallery();

    }

    private void initGuideGallery() {
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        adapter = new GalleryPagerAdapter();
        viewpager_guide_page.setAdapter(adapter);
        indicator.setViewPager(viewpager_guide_page);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == images.length - 1) {
                    btn_guide_in.setVisibility(View.VISIBLE);
                    btn_guide_in.startAnimation(fadeIn);
                } else {
                    btn_guide_in.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void setListener() {
        btn_guide_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guide_in) {
            forwardAndFinish(MainActivity.class);
        }
    }

    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageItem = new ImageView(GuideActivity.this);
            imageItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageItem.setImageResource(images[position]);
            container.addView(imageItem);
            return imageItem;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View) view);
        }
    }


}
