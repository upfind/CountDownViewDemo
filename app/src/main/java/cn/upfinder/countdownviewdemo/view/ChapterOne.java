package cn.upfinder.countdownviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ucm on 2017/7/17.
 */

public class ChapterOne extends View {

    private Paint paint = new Paint();

    public ChapterOne(Context context) {
        super(context);
        initPaint();
    }

    public ChapterOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ChapterOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    //初始化画笔
    private void initPaint() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*绘制颜色*/
//        canvas.drawColor(Color.BLACK);
        /*绘制圆*/
//        canvas.drawCircle(300, 300, 200, paint);
        /*绘制点*/
//        canvas.drawPoint(200, 200, paint);
//        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
//                500, 500,
//                500, 600,
//                500, 700
//        }, paint);

        /*绘制线*/
//        canvas.drawLine(300, 300, 500, 600, paint);
//        canvas.drawLines(new float[]{100, 200, 200, 200, 100, 300, 200, 300, 100, 400, 200, 400}, paint);
        /*绘制矩形*/
//        canvas.drawRect(100, 100, 300, 300, paint);
//        Rect rect = new Rect(300, 300, 800, 400);
//        canvas.drawRect(rect, paint);
        /*绘制圆角矩形*/
//        RectF rectF = new RectF(100, 100, 800, 400);
//        canvas.drawRoundRect(rectF, 350, 150, paint);
        /*绘制椭圆*/
//        RectF rectF = new RectF(100, 100, 800, 400);
//        canvas.drawOval(rectF, paint);
//        canvas.drawOval(100, 100, 800, 400, paint);

        /*绘制背景矩形*/
        RectF rectF = new RectF(100, 100, 800, 400);
        paint.setColor(Color.GRAY);
        canvas.drawRect(rectF, paint);

        //绘制圆弧
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 0, 90, true, paint);

    }


}
