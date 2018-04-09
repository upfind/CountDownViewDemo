package cn.upfinder.countdownview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.sql.Time;

import cn.upfinder.countdownview.R;

/**
 * Created by upfinder on 2017/8/10.
 */

public class CircleCountDownView extends View {

    private Context context;
    private Paint paintBG; //背景画笔
    private Paint paintText; //文字画笔
    private Paint paintArc; //圆弧画笔


    private int retreatType; //圆弧绘制方式（增加|减少）
    private float arcPaintWidth; //圆弧宽度
    private int circleRadius; //圆圈半径

    private int arcPaintColor = Color.parseColor("#3C3F41"); //圆弧颜色
    private int bgPaintColor = Color.parseColor("#55B2E5"); //背景颜色

    private int loadingTime; //时间 秒
    private int loadingTimeUnit = 1; //时间单位
    private String timeUnitName = "秒";
    private int textColor = Color.BLACK; //字体颜色
    private int textSize; //字体大小
    private int startLocation; //开始位置
    private float startAngle; //开始角度
    private float sweepAngleStart; //起点
    private float sweepAngleEnd; //终点
    private float sweepAngle; //扫过的角度
    private String text = "";//要绘制的文字
    private int vWidth;
    private int vHeight;

    //动画相关
    private ValueAnimator arcAnimator;
    private ValueAnimator textAnimator;


    public CircleCountDownView(Context context) {
        this(context, null);
    }

    public CircleCountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleCountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleCountDownView);
        initTypedArray(array);

        array.recycle();
        init();
    }


    public CircleCountDownView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleCountDownView);
        initTypedArray(array);

        array.recycle();
        init();
    }


    private void initTypedArray(TypedArray array) {
        retreatType = array.getInt(R.styleable.CircleCountDownView_ccdv_retreat_type, 1);
        startLocation = array.getInt(R.styleable.CircleCountDownView_ccdv_start_location, 1);
        circleRadius = (int) array.getDimension(R.styleable.CircleCountDownView_ccdv_radius, dp2px(25));
        arcPaintWidth = array.getDimension(R.styleable.CircleCountDownView_ccdv_arc_width, dp2px(3));
        arcPaintColor = array.getColor(R.styleable.CircleCountDownView_ccdv_arc_color, arcPaintColor);

        textSize = (int) array.getDimension(R.styleable.CircleCountDownView_ccdv_text_size, dp2px(14));
        textColor = array.getColor(R.styleable.CircleCountDownView_ccdv_text_color, textColor);
        bgPaintColor = array.getColor(R.styleable.CircleCountDownView_ccdv_bg_color, bgPaintColor);
        loadingTime = array.getInteger(R.styleable.CircleCountDownView_ccdv_time, 3);
        loadingTimeUnit = array.getInt(R.styleable.CircleCountDownView_ccdv_time_unit, 1);
        switch (loadingTimeUnit) {
            case 1:
                timeUnitName = "秒";
                break;
            case 2:
                timeUnitName = "毫秒";
                break;
            case 3:
                timeUnitName = "分钟";
                break;

        }
    }

    private void init() {
        //设置背景为透明 使其成为圆形view
        this.setBackground(ContextCompat.getDrawable(context, android.R.color.transparent));
        paintBG = new Paint();
        paintBG.setStyle(Paint.Style.FILL);
        paintBG.setAntiAlias(true);
        paintBG.setColor(bgPaintColor);

        paintArc = new Paint();
        paintArc.setStyle(Paint.Style.STROKE);
        paintArc.setAntiAlias(true);
        paintArc.setColor(arcPaintColor);
        paintArc.setStrokeWidth(arcPaintWidth);

        paintText = new Paint();
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setAntiAlias(true);
        paintText.setColor(textColor);
        paintText.setTextSize(textSize);

        if (loadingTime < 0) {
            loadingTime = 3;
        }
        if (startLocation == 1) {
            startAngle = -180;
        } else if (startLocation == 2) {
            startAngle = -90;
        } else if (startLocation == 3) {
            startAngle = 0;
        } else if (startLocation == 4) {
            startAngle = 90;
        }


        if (retreatType == 1) {
            sweepAngleStart = 0f;
            sweepAngleEnd = 360f;
        } else {
            sweepAngleStart = 360f;
            sweepAngleEnd = 0f;
        }


        arcAnimator = ValueAnimator.ofFloat(sweepAngleStart, sweepAngleEnd);
        textAnimator = ValueAnimator.ofInt(loadingTime, 0);
        arcAnimator.setInterpolator(new LinearInterpolator());
        arcAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取到需要绘制的角度，重新绘制
                sweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        textAnimator.setInterpolator(new LinearInterpolator());
        textAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int time = (int) valueAnimator.getAnimatedValue();
                text = time + timeUnitName;
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取view宽高
        vWidth = w;
        vHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //因为必须是圆形的View 所以在这里重新赋值
        setMeasuredDimension(circleRadius * 2, circleRadius * 2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画出背景
        canvas.drawCircle(vWidth / 2, vHeight / 2, vWidth / 2 - arcPaintWidth, paintBG);
        //画圆弧
        RectF rectF = new RectF(arcPaintWidth, arcPaintWidth, vWidth - arcPaintWidth, vHeight - arcPaintWidth);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paintArc);
        //画文字
        float textWidth = paintText.measureText(text, 0, text.length());
        float dx = vWidth / 2 - textWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = paintText.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = vHeight / 2 + dy;
        canvas.drawText(text, dx, baseLine, paintText);
    }


    /*开始动画*/
    public void start() {

        //防止重复执行
        if (arcAnimator.isRunning() || textAnimator.isRunning()) {
            return;
        }

        AnimatorSet set = new AnimatorSet();
        set.playTogether(arcAnimator, textAnimator);
        int time = loadingTime * 1000;
        switch (loadingTimeUnit) {
            case 1: //秒
                time = loadingTime * 1000;
                break;
            case 2: //毫秒
                time = loadingTime;
                break;
            case 3: //分钟
                time = loadingTime * 1000 * 60;
                break;
        }
        set.setDuration(time);
        set.setInterpolator(new LinearInterpolator());
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                clearAnimation();
                if (onFinishListener != null) {
                    onFinishListener.onFinish();
                }

            }
        });

    }

    private OnFinishListener onFinishListener;

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }


    /*倒计时完成回调接口*/
    public interface OnFinishListener {
        void onFinish();
    }


    /*
         * dp转换成px
         * */
    public int dp2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
