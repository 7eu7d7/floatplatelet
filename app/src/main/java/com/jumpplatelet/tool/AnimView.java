package com.jumpplatelet.tool;

import android.content.*;
import android.view.*;
import android.graphics.*;
import android.content.res.*;
import java.io.*;
import android.widget.*;

public class AnimView extends View
{
	Animation anim;
	AssetManager am;
	int delay=40;
	long start;
	
	public AnimView(Context c){
		super(c);
		am=c.getAssets();
		setLayoutParams(new ViewGroup.LayoutParams(698,540));
		
		anim=new Animation(loadbmps(),698,540);
		anim.setCycle(520);
		anim.setDelay(delay);
		anim.start();
		
		render.start();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		anim.Draw(canvas);
		super.onDraw(canvas);
	}
	
	Thread render=new Thread(){
		@Override
		public void run()
		{
			while(true){
				start=System.currentTimeMillis();
				postInvalidate();
				try
				{
					Thread.sleep(delay - (System.currentTimeMillis() - start));
				}
				catch (InterruptedException e)
				{}
				anim.next();
			}
		}
	};
	
	public Bitmap[] loadbmps(){
		Bitmap[] bmps=new Bitmap[13];//总共13张图片
		try
		{
			for(int i=0;i<bmps.length;i++){
				bmps[i] = BitmapFactory.decodeStream(am.open("platelet" + i + ".png"));
			}
		}
		catch (IOException e)
		{}
		return bmps;
	}
}
