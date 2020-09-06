package com.yandex.smur.marina.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    interface TouchActionListener {
        void onTouchDown(int x, int y);
    }

    public static int radius2 = 0;
    public static int radius1 = 0;
    private int color1 = 0;
    private int color2 = 0;
    private int color3 = 0;
    private int color4 = 0;
    private int color5 = 0;

    public static int widthCenter = 0;
    public static int heightCenter = 0;

    private Paint paint;

    private Path path1;
    private Paint paint1;
    private RectF oval1;

    private Path path2;
    private Paint paint2;
    private RectF oval2;

    private Path path3;
    private Paint paint3;
    private RectF oval3;

    private Path path4;
    private Paint paint4;
    private RectF oval4;


    private TouchActionListener touchActionListener;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttrs(attrs);
        init(attrs);
    }
    private void getAttrs (AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewParams);
            radius2 = typedArray.getDimensionPixelSize(R.styleable.CustomViewParams_circle_radius, 0);
            radius1 = typedArray.getDimensionPixelSize(R.styleable.CustomViewParams_circle_radius2, 0);
            color1 = typedArray.getColor(R.styleable.CustomViewParams_color1, 0);
            color2 = typedArray.getColor(R.styleable.CustomViewParams_color2, 0);
            color3 = typedArray.getColor(R.styleable.CustomViewParams_color3, 0);
            color4 = typedArray.getColor(R.styleable.CustomViewParams_color4, 0);
            color5 = typedArray.getColor(R.styleable.CustomViewParams_color5, 0);
            typedArray.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widthCenter = w / 2;
        heightCenter = h / 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init (@Nullable AttributeSet attrs) {
        paint = new Paint();

        path1 = new Path();
        paint1 = new Paint();
        paint1.setColor(color2);
        oval1 = new RectF();

        path2 = new Path();
        paint2 = new Paint();
        paint2.setColor(color3);
        oval2 = new RectF();

        path3 = new Path();
        paint3 = new Paint();
        paint3.setColor(color4);
        oval3 = new RectF();

        path4 = new Path();
        paint4 = new Paint();
        paint4.setColor(color5);
        oval4 = new RectF();
    }

    public void swapAllColor() {
        paint1.setColor(paint1.getColor() == color2 ? color3 : color2);
        paint2.setColor(paint2.getColor() == color3 ? color4 : color3);
        paint3.setColor(paint3.getColor() == color4 ? color5 : color4);
        paint4.setColor(paint4.getColor() == color5 ? color2 : color5);
        postInvalidate();
    }

    public void swapColorFirstSector() {
        paint1.setColor(paint1.getColor() == color2 ? color3 : color2);
        postInvalidate();
    }

    public void swapColorSecondSector() {
        paint2.setColor(paint2.getColor() == color3 ? color4 : color3);
        postInvalidate();
    }

    public void swapColorThirdSector() {
        paint3.setColor(paint3.getColor() == color4 ? color5 : color4);
        postInvalidate();
    }

    public void swapColorForthSector() {
        paint4.setColor(paint4.getColor() == color5 ? color2 : color5);
        postInvalidate();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {

        path1.addCircle(widthCenter, heightCenter, radius1, Path.Direction.CW);
        paint1.setStrokeWidth(5);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setAntiAlias(true);
        oval1.set(widthCenter - radius1, heightCenter - radius1, widthCenter+radius1,
                heightCenter + radius1);
        canvas.drawArc(oval1, 180, 90, true, paint1);

        path2.addCircle(widthCenter, heightCenter, radius1, Path.Direction.CW);
        paint2.setStrokeWidth(5);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        oval2.set(widthCenter - radius1, heightCenter - radius1, widthCenter+radius1,
                heightCenter + radius1);
        canvas.drawArc(oval2, 270, 90, true, paint2);

        path3.addCircle(widthCenter, heightCenter, radius1, Path.Direction.CW);
        paint3.setStrokeWidth(5);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setAntiAlias(true);
        oval3.set(widthCenter - radius1, heightCenter - radius1, widthCenter+radius1,
                heightCenter + radius1);
        canvas.drawArc(oval3, 0, 90, true, paint3);

        path4.addCircle(widthCenter, heightCenter, radius1, Path.Direction.CW);
        paint4.setStrokeWidth(5);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setAntiAlias(true);
        oval4.set(widthCenter - radius1, heightCenter - radius1, widthCenter+radius1,
                heightCenter + radius1);
        canvas.drawArc(oval3, 90, 90, true, paint4);

        paint.setColor(color1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(widthCenter, heightCenter, radius2, paint);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchActionListener != null) {
                touchActionListener.onTouchDown((int) event.getX(), (int) event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    public void setTouchActionListener(TouchActionListener touchActionListener) {
        this.touchActionListener = touchActionListener;
    }
}
