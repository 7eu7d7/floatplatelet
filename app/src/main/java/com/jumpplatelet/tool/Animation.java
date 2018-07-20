package com.jumpplatelet.tool;
import android.graphics.*;

public class Animation
{
	Paint pai=new Paint();
	Bitmap[] frames;//每一帧图片
	RectF size=new RectF();//动画绘制区域
	Rect srcsize=new Rect();//图片大小
	int nowtime,cycletime,delay=20;
	boolean looping;
	
	public Animation(){}
	public Animation(Bitmap[] frames,int w,int h){
		setFrames(frames);
		setSize(w,h);
	}
	
	//绘图函数
	public void Draw(Canvas canvas){
		canvas.drawBitmap(frames[frames.length*nowtime/cycletime],srcsize,size,pai);
	}
	
	//下一时刻
	public void next(){
		if(!looping)return;
		nowtime+=delay;
		nowtime%=cycletime;
	}
	
	public void setFrames(Bitmap[] frames){//设置每一帧
		this.frames=frames;
		srcsize.set(0,0,frames[0].getWidth(),frames[0].getHeight());
	}
	public void setPosition(int x,int y){//设置位置
		size.offsetTo(x,y);
	}
	public void setSize(int w,int h){//设置大小
		size.right=size.left+w;
		size.bottom=size.top+h;
	}
	public void setCycle(int cyc){//设置周期
		cycletime=cyc;
	}
	public void setDelay(int time){//设置每帧间隔
		delay=time;
	}
	public void setfps(float fps){//设置帧率
		delay=(int)(1000/fps);
	}
	public void start(){
		looping=true;
	}
	public void stop(){
		looping=false;
	}
	public void reset(){
		nowtime=0;
	}
}
