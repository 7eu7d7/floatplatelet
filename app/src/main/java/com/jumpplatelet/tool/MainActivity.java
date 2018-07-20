package com.jumpplatelet.tool;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import java.io.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setwmparams(0,0);
		showFloatView(new AnimView(this));
		finish();
    }
	
	
	WindowManager wm;
	WindowManager.LayoutParams params;
	float fx,fy;
	public static boolean isadd;
	
	private void setwmparams(int x,int y){
		wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();
		params.type = 2010;
		params.format = PixelFormat.RGBA_8888;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		params.width=698;
		params.height=540;
		params.x = x;
		params.y = y;
	}
	
	private void showFloatView(final View floatView) {
		if(!isadd){//防止重复添加
			wm.addView(floatView, params);
			isadd=true;
		}
		// 设置悬浮窗的Touch监听，跟随手指移动
		floatView.setOnTouchListener(new OnTouchListener() {
				int lastX, lastY;
				int paramX, paramY;
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							lastX = (int) event.getRawX();
							lastY = (int) event.getRawY();
							paramX = params.x;
							paramY = params.y;
							break;
						case MotionEvent.ACTION_MOVE:
							int dx = (int) event.getRawX() - lastX;
							int dy = (int) event.getRawY() - lastY;
							params.x = paramX + dx;
							params.y = paramY + dy;
							fx=params.x;fy=params.y;
							//更新悬浮窗位置
							wm.updateViewLayout(floatView, params);
							break;
						case MotionEvent.ACTION_UP:

							break;
					}
					return true;
				}
			});
	}
}
