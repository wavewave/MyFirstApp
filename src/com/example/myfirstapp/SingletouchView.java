package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SingletouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    
    private OnHoverListener ohl = new OnHoverListener() { 
	     @Override 
              public boolean onHover( View v, MotionEvent ev ) {
		 // Log.i( TAG, ev.toString() ); 
		 printSamples ( ev) ;
                  switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
			// path.moveTo(eventX, eventY);
			Log.i(TAG,"hover down");
	                return true;
                    case MotionEvent.ACTION_MOVE:
                        //Log.i(TAG,"hover move");
                	return true;
                    case MotionEvent.ACTION_UP:
                	  // nothing to do
		        Log.i(TAG,"hover up"); 
	                return true;
		  }

		  return true;                                          
              }
	};

  
    void printSamples(MotionEvent ev) {
	final int historySize = ev.getHistorySize();
	final int pointerCount = ev.getPointerCount();
	for (int h = 0; h < historySize; h++) {
	    Log.i(TAG, "At time " +  String.valueOf (ev.getHistoricalEventTime(h)));
	    for (int p = 0; p < pointerCount; p++) {
		Log.i(TAG, "  pointer " + String.valueOf (ev.getHistoricalX(p,h)) + " "  + String.valueOf (ev.getHistoricalY(p,h)));
	    }
	}  
	Log.i(TAG,"At time " + String.valueOf (ev.getEventTime()));
	for (int p = 0; p < pointerCount; p++) {
	    Log.i(TAG, "  pointer " + String.valueOf (ev.getX(p)) + String.valueOf (ev.getY(p)));
	}
    }

    private static final String TAG = "SingletouchView"; 
    public SingletouchView(Context context, AttributeSet attrs) {
	super(context, attrs);
        Log.i(TAG,"startview");
        this.setOnHoverListener( ohl );
	paint.setAntiAlias(true);
	paint.setStrokeWidth(1f);
	paint.setColor(Color.WHITE);
	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeJoin(Paint.Join.ROUND); 
    }


  private float px = 0; 
  private float py = 0; 

  
  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawPath(path, paint);
  }
  
  @Override
  public boolean onGenericMotionEvent( MotionEvent event ) {
      Log.i(TAG,event.toString());
      return true;
  }
  @Override
  public boolean onTouchEvent(MotionEvent ev) {
      // Log.i(TAG, event.toString()) ;
      float eventX = ev.getX();
      float eventY = ev.getY();
      px = eventX; 
      py = eventY; 

      switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
          // path.moveTo(eventX, eventY);
	  return true;
      case MotionEvent.ACTION_MOVE:
          //Log.i(TAG, event.toString());

	  final int historySize = ev.getHistorySize();
	  final int pointerCount = ev.getPointerCount();
	  for (int h = 0; h < historySize; h++) {
	    for (int p = 0; p < pointerCount; p++) {
                float nx = ev.getHistoricalX(p,h);
                float ny = ev.getHistoricalY(p,h);
                path.moveTo ( px, py);
 		path.lineTo( nx , ny );
                invalidate();
                 px = nx ; 
                py = ny; 
	    }
	  }

	  // path.lineTo(eventX, eventY);
	  break;
      case MotionEvent.ACTION_UP:
	  // nothing to do
	  break;
      default:
	  return false;
      }

      // Schedules a repaint.
      return true;
      } 

}
