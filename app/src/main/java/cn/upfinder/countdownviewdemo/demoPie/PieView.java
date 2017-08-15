package cn.upfinder.countdownviewdemo.demoPie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ucm on 2017/7/28.
 */

public class PieView extends View {


    //颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //饼状图初始绘制角度
    private float startAngle = 0;
    //数据
    private ArrayList<pieData> data;
    //宽度
    private int mWidth, mHeight;
    //画笔
    private Paint paint = new Paint();


    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data == null) {
            return;
        }
        float currentStartAngle = startAngle;                     //当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);                //将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);

    }
}
