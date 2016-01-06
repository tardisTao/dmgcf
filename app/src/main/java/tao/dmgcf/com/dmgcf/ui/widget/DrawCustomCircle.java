package tao.dmgcf.com.dmgcf.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.MutableChar;
import android.view.View;

/**
 * Created by tao on 2016/1/6.
 */
public class DrawCustomCircle extends View {

      Paint innerPaint,outerPaint;
      Context context;

    public DrawCustomCircle(Context context) {
        this(context, null);
    }
    public DrawCustomCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.innerPaint = new Paint();
        this.innerPaint.setAntiAlias(true); //消除锯齿
        this.innerPaint.setStyle(Paint.Style.STROKE); //绘制空心圆

        this.outerPaint = new Paint();
        this.outerPaint.setAntiAlias(true); //消除锯齿
        this.outerPaint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth()/2;
        int innerCircle = dip2px(context, 70); //设置内圆半径
        int ringWidth = dip2px(context, 10); //设置圆环宽度

        //设置外圆画笔颜色
        this.outerPaint.setColor(Color.parseColor("#FD5353"));
        this.outerPaint.setStrokeWidth(5);//设置圆的厚度
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        outerPaint.setPathEffect(effects);
        canvas.drawCircle(center,center, innerCircle, this.outerPaint);//以该圆为半径向内外扩展至厚度为10px


        //绘制外圆
        this.innerPaint.setColor(Color.parseColor("#FD5353"));
        this.innerPaint.setStrokeWidth(8);
        canvas.drawCircle(center,center, innerCircle-dip2px(context,12), this.innerPaint);

        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
