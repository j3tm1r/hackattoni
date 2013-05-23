package it.polito.hackattoni.visualizzazioni;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class VistaTorta extends View {

	private double values[] = { 0.1, 0.35, 0.05, 0.1, 0.05, 0.02, 0.30, 0.03 };
	private double mRadiuses[];
	private double mAngles[];
	private double mAngleSum;
	private double min = 0;
	private double max = values[0];

	private double mDrawAngle;

	private RectF mOval;

	private Paint mPaint;
	private Paint mFillPaint;
	private Path mPath;

	public VistaTorta(Context context) {
		this(context, null, 0);
	}

	public VistaTorta(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VistaTorta(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		double value;
		mAngleSum = 0;
		mRadiuses = new double[values.length];
		mAngles = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			value = values[i];
			mRadiuses[i] = Math.sqrt(value);
			mAngles[i] = Math.sqrt(value);
			mAngleSum += mAngles[i];
			if (value < min)
				min = value;
			else if (value > max)
				max = value;
		}

		mPaint = new Paint();
		mPaint.setAntiAlias(true);

		mPaint.setStrokeWidth(2);
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.BLUE);

		mFillPaint = new Paint();
		mFillPaint.setAntiAlias(true);
		mFillPaint.setStyle(Style.FILL);
		mFillPaint.setColor(Color.LTGRAY);

		mPath = new Path();

		mOval = new RectF();
		// mPaint.setPathEffect(new CornerPathEffect(10));
	}

	private float mLeft, mTop, mRight, mBottom, mWidth, mHeight, mBigW, mRad;

	int myColor;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float[] hsvColor = { 0, 0, 0 };
		mDrawAngle = 0;
		mWidth = getWidth();
		mHeight = getHeight();
		mBigW = (float) (0.8*(mWidth < mHeight ? mWidth : mHeight));

		for (int i = 0; i < values.length; i++) {
			// resetto il path
			mPath.reset();
			mRad = (float) mRadiuses[i] * mBigW;
			// Calcolo il rettangolo che contiene il settore
			mLeft = mWidth / 2 - mRad;
			mTop = mHeight / 2 - mRad;
			mRight = mWidth / 2 + mRad;
			mBottom = mHeight / 2 + mRad;

			mOval.set(mLeft, mTop, mRight, mBottom);

			// aggiungo l'arco

			mPath.addArc(mOval, (float) mDrawAngle,
					(float) ((mAngles[i] / mAngleSum) * 360));
			mPath.lineTo(mWidth / 2, mHeight / 2);
			mPath.close();
			mDrawAngle += (mAngles[i] / mAngleSum) * 360;

			Log.d("AngoloDisegnato", " " + mDrawAngle);
			// disegno

			// Color.colorToHSV(Color.RED, hsvColor);
			// hsvColor[1] = (float) (1 - (max - values[i]) / (max - min));
			// hsvColor[2] = (float) (1 - mAngles[i] / mAngleSum);
			//
			// mFillPaint.setColor(Color.HSVToColor(hsvColor));
			canvas.drawPath(mPath, mFillPaint);
			canvas.drawPath(mPath, mPaint);
		}
	}
}
