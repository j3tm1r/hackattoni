package it.polito.hackattoni.visualizzazioni;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class VistaTorta extends View {

	private double values[] = { 0.1, 0.3, 0.2, 0.1, 0.05, 0.02, 0.13, 0.1 };
	private double mRadiuses[];
	private double mAngles[];
	private double mAngleSum;

	private double mDrawAngle;

	private RectF mOval;

	private Paint mPaint;
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
		for (int i = 0; i < values.length; i++) {
			value = values[i];
			mRadiuses[i] = Math.sqrt(value) * getWidth() / 2;
			mAngles[i] = Math.sqrt(value);
			mAngleSum += mAngles[i];
		}

		mPaint = new Paint();
		mPaint.setAntiAlias(true);

		mPaint.setStrokeWidth(10);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setColor(Color.BLUE);
		mPath = new Path();

		mOval = new RectF();
		// mPaint.setPathEffect(new CornerPathEffect(10));
	}

	private float mLeft, mTop, mRight, mBottom, mWidth, mHeight;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mDrawAngle = 0;
		mWidth = getWidth();
		mHeight = getHeight();

		for (int i = 0; i < values.length; i++) {
			// resetto il path
			mPath.reset();

			// Calcolo il rettangolo che contiene il settore
			mLeft = mWidth / 2 - (float) mRadiuses[i];
			mTop = mHeight / 2 + (float) mRadiuses[i];
			mRight = mWidth / 2 - (float) mRadiuses[i];
			mBottom = mHeight / 2 + (float) mRadiuses[i];

			mOval.set(mLeft, mTop, mRight, mBottom);

			// aggiungo l'arco
			mPath.addArc(mOval, (float) mDrawAngle,
					(float) ((mAngles[i] / mAngleSum) * Math.PI * 2));

			// disegno
			canvas.drawPath(mPath, mPaint);
		}
	}
}
