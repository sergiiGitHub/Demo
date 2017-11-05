package com.example.sergii.demodecorationpopup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by sergii on 04.11.17.
 */

public class DecorateFrameLayout extends FrameLayout {

    private static final String TAG = DecorateFrameLayout.class.getSimpleName();

    private float topLine[];
    private float bottomLine[];
    private float leftLine[];
    private float rightLine[];
    private float arrowLine[];

    private int lineWidth = 2;
    private int lineWidthHalf = lineWidth/2;
    private int offset = 10;
    private int arrowSize = 20;
    private int halfArrowSize = arrowSize/2;
    private Paint paint;
    private Paint paintFill;
    private float[] arrowFill;
    private Path arrowFillPath;

    public DecorateFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public DecorateFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DecorateFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        initPaint();
        initLines();
    }

    private void initLines() {
        arrowLine = new float[8];
        arrowFill = new float[10];
        arrowFillPath = new Path();
        paint.setAntiAlias(true);

        topLine = new float[8];
        bottomLine = new float[4];
        leftLine = new float[4];
        rightLine = new float[4];
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);

        paintFill = new Paint();
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateLines(w, h);
    }

    private void updateLines(int w, int h) {
        Log.d(TAG, "updateLines: " + w + " " + h);
        //arrow
        arrowLine[0] = w / 2 - halfArrowSize;
        arrowLine[1] = offset - lineWidthHalf;
        arrowLine[2] = w / 2 - lineWidthHalf;
        arrowLine[3] = lineWidthHalf;

        arrowLine[4] = w / 2 - lineWidthHalf;
        arrowLine[5] = lineWidthHalf;
        arrowLine[6] = w / 2 + halfArrowSize;
        arrowLine[7] = offset - lineWidthHalf;

        //arrowFill
        arrowFill[0] = w / 2 - halfArrowSize;
        arrowFill[1] = offset - lineWidthHalf;
        arrowFill[2] = w / 2 - lineWidthHalf;
        arrowFill[3] = lineWidthHalf;

        arrowFill[4] = w / 2 - lineWidthHalf;
        arrowFill[5] = lineWidthHalf;
        arrowFill[6] = w / 2 + halfArrowSize;
        arrowFill[7] = offset - lineWidthHalf;

        arrowFill[8] = w / 2 - halfArrowSize;
        arrowFill[9] = offset - lineWidthHalf;
        arrowFillPath.moveTo(arrowFill[0], arrowFill[1]);
        for (int i = 1; i < arrowFill.length / 2; ++i) {
            arrowFillPath.lineTo(arrowFill[i * 2], arrowFill[i * 2 + 1] );
        }
        arrowFillPath.close();

        //top
        topLine[0] = lineWidthHalf;
        topLine[1] = offset - lineWidthHalf;
        topLine[2] = w /2 - halfArrowSize;
        topLine[3] = offset - lineWidthHalf;

        topLine[4] = w / 2 + halfArrowSize;
        topLine[5] = offset - lineWidthHalf;
        topLine[6] = w - lineWidthHalf;
        topLine[7] = offset - lineWidthHalf;

        //bottom
        bottomLine[0] = lineWidthHalf;
        bottomLine[1] = h - lineWidthHalf;
        bottomLine[2] = w - lineWidthHalf;
        bottomLine[3] = h - lineWidthHalf;

        //left
        leftLine[0] = lineWidthHalf;
        leftLine[1] = offset - lineWidthHalf;
        leftLine[2] = lineWidthHalf;
        leftLine[3] = h - lineWidthHalf;

        //right
        rightLine[0] = w - lineWidthHalf;
        rightLine[1] = offset - lineWidthHalf;
        rightLine[2] = w - lineWidthHalf;
        rightLine[3] = h - lineWidthHalf;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);

        canvas.drawRect(leftLine[0], leftLine[1], rightLine[2], rightLine[3], paintFill );

        canvas.drawLines(topLine, paint);
        canvas.drawLines(bottomLine, paint);
        canvas.drawLines(leftLine, paint);
        canvas.drawLines(rightLine, paint);

        canvas.drawPath(arrowFillPath, paintFill );
        canvas.drawLines(arrowLine, paint);
    }
}
