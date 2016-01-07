package tao.dmgcf.com.dmgcf.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tao on 2016/1/6.
 */
public class DrawCustomCircle extends View {

    Paint innerPaint, outerPaint, outerSmallPaint,textPaint;
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

        this.outerSmallPaint = new Paint();
        this.outerSmallPaint.setAntiAlias(true); //消除锯齿


        //设置字体样式
        this.textPaint = new Paint();
        this.textPaint.setAntiAlias(true); //消除锯齿
        this.textPaint.setColor(Color.parseColor("#FD5353"));
        this.textPaint.setFakeBoldText(true);
        this.textPaint.setTextSize(20);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        int center = (getWidth() - dip2px(context, 20)) / 2;
        int innerCircle = dip2px(context, 75); //设置内圆半径
        int ringWidth = dip2px(context, 10); //设置圆环宽度


        canvas.save();
        //设置外圆画笔颜色
        this.outerPaint.setColor(Color.parseColor("#FD5353"));
        this.outerPaint.setStrokeWidth(5);//设置圆的厚度
        PathEffect effects = new DashPathEffect(new float[]{1, 11, 1, 11}, 2);
        outerPaint.setPathEffect(effects);
        //设置画笔的样式  圆形样式Cap.ROUND, 方形样式Cap.SQUARE
        outerPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawCircle(center, center, innerCircle, this.outerPaint);//以该圆为半径向内外扩展至厚度为10px
        canvas.restore();

        //设置内圆字体
        outerPaint.setTextAlign(Paint.Align.CENTER);
        outerPaint.setColor(Color.parseColor("#FD5353"));
        outerPaint.setStrokeWidth(0);
        outerPaint.setTextSize(26);
        canvas.drawText("年化收益率", center, center + dip2px(context, 30), outerPaint);


        //绘制内圆
        canvas.save();
        this.innerPaint.setColor(Color.parseColor("#FD5353"));
        this.innerPaint.setStrokeWidth(10);
        //设置画笔的样式  圆形样式Cap.ROUND, 方形样式Cap.SQUARE
        innerPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawCircle(center, center, innerCircle - dip2px(context, 14), this.innerPaint);
        canvas.restore();
        //设置内圆字体
        innerPaint.setTextAlign(Paint.Align.CENTER);
        innerPaint.setColor(Color.parseColor("#FD5353"));
        innerPaint.setStrokeWidth(0);
        innerPaint.setTextSize(26);

        canvas.drawText("9.00~9.50%", center, center + dip2px(context, 10), innerPaint);



        //绘制外环实心小圆
        canvas.save();
        float smallPointX = (float) (center + dip2px(context, 5) + innerCircle * Math.cos(dip2px(context, -18) * 3.14 / 180));
        float smallPointY = (float) (center + dip2px(context, 5) + innerCircle * Math.sin(dip2px(context, -18) * 3.14 / 180));
        this.outerSmallPaint.setStyle(Paint.Style.FILL); //绘制实心圆
        this.outerSmallPaint.setColor(Color.parseColor("#FFFFFF"));
        this.outerSmallPaint.setStrokeWidth(5);
        canvas.drawCircle(smallPointX, smallPointY, dip2px(context, 15), outerSmallPaint);
        canvas.restore();

        //绘制实心小圆外环
        canvas.save();
        this.outerSmallPaint.setStyle(Paint.Style.STROKE);
        this.outerSmallPaint.setStrokeWidth(0.5f);
        this.outerSmallPaint.setColor(Color.parseColor("#999999"));
        canvas.drawCircle(smallPointX, smallPointY, dip2px(context, 14), outerSmallPaint);
        canvas.restore();
        //设置字体居中对齐
        outerSmallPaint.setTextAlign(Paint.Align.CENTER);
        outerSmallPaint.setColor(Color.parseColor("#FD5353"));
        outerSmallPaint.setStrokeWidth(0);
        outerSmallPaint.setTextSize(30);
        canvas.drawText("3%", smallPointX, smallPointY + dip2px(context, 3), outerSmallPaint);


        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
