package com.android.example.graphics;

//import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.View;


class GraphicsView extends View {
	
	private static final String QUOTE = "Now is the time for all " +
			"good men to come to the aid of their country.";
	
	public GraphicsView(Context context) {
		super(context);
	}
	@Override
	protected void onDraw(Canvas canvas) {
	// Drawing commands go here
		//int color = getResources().getColor( R.color.background_light );
		//int color = getResources().getColor( R.color.mycolor );
		//setBackgroundColor(color);
		setBackgroundResource(R.color.mycolor);
		
		Path circle = new Path();
		circle.addCircle(150, 150, 100, Direction.CW);
		
		Paint cPaint = new Paint();
		cPaint.setColor(Color.LTGRAY);
		
		Paint tPaint = new Paint();
		tPaint.setColor( Color.BLACK );
		
		canvas.drawPath(circle, cPaint);
		canvas.drawTextOnPath(QUOTE, circle, 30, 20, tPaint);
		
	}
}

