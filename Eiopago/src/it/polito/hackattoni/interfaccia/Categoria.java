package it.polito.hackattoni.interfaccia;

import android.R.color;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.*;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;

public class Categoria extends View {
	
	

	private int lenght;
	private String name;
	private Rect barra;
	private Paint myPaint;
	private Paint textPaint;
	
	public Categoria(Context ctx){
		this(ctx,null,0);
	}
	public Categoria(Context ctx, AttributeSet attrs){
		this(ctx,attrs,0);
	}
	
	public Categoria(Context ctx, AttributeSet attrs,int style){
		super(ctx,attrs,style);
		myPaint = new Paint();
		textPaint = new Paint();

		myPaint.setStrokeWidth(10);
		myPaint.setStyle(Style.FILL_AND_STROKE);
		myPaint.setPathEffect(new CornerPathEffect(10));
		myPaint.setShadowLayer(5, 3, 3, 0x80000000);
		barra= new Rect(0,0,0,0);
		
		textPaint.setStrokeWidth(3);
		textPaint.setStyle(Style.STROKE);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(10);
		textPaint.setTextAlign(Align.RIGHT);
		
	}
	
	@Override
	public void onDraw(Canvas canvas){
		int w=getWidth();
		int h=getHeight();
		barra.set(getPaddingLeft(),getPaddingTop(),(w-getPaddingLeft()-getPaddingRight())*lenght/100,h-getPaddingTop()-getPaddingBottom());
		canvas.drawRect(barra, myPaint);
		canvas.drawText(name, w, h, textPaint);
		textPaint.setColor(Color.WHITE);
		textPaint.setStrokeWidth(5);
		canvas.drawText(name, w, h, textPaint);
		
		
	}	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mtw=MeasureSpec.getMode(widthMeasureSpec);
		int mth=MeasureSpec.getMode(heightMeasureSpec);
		int w=MeasureSpec.getSize(widthMeasureSpec);
		int h=MeasureSpec.getSize(heightMeasureSpec);
		
		if(h<getPaddingTop()+getPaddingBottom()+40){
			h=getPaddingBottom()+getPaddingTop()+40;
		}
		setMeasuredDimension(w, h);
	}

	public int getColor() {
		return myPaint.getColor();
	}
	public void setColor(int color) {
		
		myPaint.setColor(color);
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
