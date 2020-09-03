package com.yandex.smur.marina.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private int radius2 = 0;
    private int radius1 = 0;
    private int color = 0;
    private int color2 = 0;

    private int widthCenter = 0;
    private int heightCenter = 0;

    private Paint paint = new Paint();
    private Paint paint2 = new Paint();


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttrs(attrs);
    }
    private void getAttrs (AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewParams);
            radius2 = typedArray.getDimensionPixelSize(R.styleable.CustomViewParams_circle_radius, 0);
            radius1 = typedArray.getDimensionPixelSize(R.styleable.CustomViewParams_circle_radius2, 0);
            color = typedArray.getColor(R.styleable.CustomViewParams_circle_color, 0);
            color2 = typedArray.getColor(R.styleable.CustomViewParams_color1, 0);
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
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
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

    @Override
    protected void onDraw(Canvas canvas) {
        paint2.setColor(color2);
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(widthCenter, heightCenter, radius1, paint2);

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(widthCenter, heightCenter, radius2, paint);

//        Path path = new Path();
//        path.moveTo(widthCenter-radius2, heightCenter);
//        path.lineTo(widthCenter-radius1, heightCenter);
//        path.addArc(new RectF(widthCenter-radius1, heightCenter-radius1, widthCenter
//                +radius1, heightCenter+radius1), 180, 270);
//        path.lineTo(widthCenter, heightCenter-radius2);
//        path.addArc(new RectF(widthCenter-radius2, heightCenter, widthCenter,
//                heightCenter-radius2), 180, 270);
//        path.close();

//        canvas.drawPath(path, paint2);

        super.onDraw(canvas);
    }

}
