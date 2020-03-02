package com.carson.rule30.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.core.content.ContextCompat
import com.carson.rule30.data.models.WorldPoint
import kotlin.math.max
import kotlin.math.min


class WorldView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var mScaleDetector: ScaleGestureDetector? = null
    var mScaleFactor = 1f
    var mScalePointX = 0f
    var mScalePointY = 0f

    private var mPositionX = 0f
    private var mPositionY = 50f
    private var mLastTouchX = 0f
    private var mLastTouchY = 0f
    private val mMinZoom = 0.5f
    private val mMaxZoom = 5.0f

    private val mPaint = Paint()

    private var mPoints: List<WorldPoint> = arrayListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredHeight, measuredWidth)
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initialize()
    }

    private fun initialize() {
        mPaint.color = ContextCompat.getColor(context, android.R.color.black)
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true

        mScaleDetector = ScaleGestureDetector(context, ScaleListener())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        // Scale canvas to the scale factor and scale point.
        canvas.scale(mScaleFactor, mScaleFactor, mScalePointX, mScalePointY)
        // Translate the canvas to the saved position.
        canvas.translate(mPositionX, mPositionY)

        // Draw a circle for every point in the points array.
        for (point in mPoints) {
            canvas.drawCircle(
                point.xCoord,
                point.yCoord,
                point.radius,
                mPaint
            )
        }

        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //the scale gesture detector should inspect all the touch events
        mScaleDetector?.onTouchEvent(event)
        val action = event.action
        when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                //get x and y cords of where we touch the screen
                val x = (event.x - mScalePointX) / mScaleFactor
                val y = (event.y - mScalePointY) / mScaleFactor

                //remember where touch event started
                mLastTouchX = x
                mLastTouchY = y

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (event.x - mScalePointX) / mScaleFactor
                val y = (event.y - mScalePointY) / mScaleFactor
                if (mScaleDetector?.isInProgress == false) {
                    //calculate the distance in x and y directions
                    val distanceX: Float = x - mLastTouchX
                    val distanceY: Float = y - mLastTouchY
                    mPositionX += distanceX
                    mPositionY += distanceY
                    //redraw canvas call onDraw method
                    invalidate()
                }
                //remember this touch position for next move event
                mLastTouchX = x
                mLastTouchY = y
                return true
            }
            MotionEvent.ACTION_UP -> {
                mLastTouchX = 0f
                mLastTouchY = 0f
                invalidate()
                return true
            }
        }
        return true
    }

    inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor
            mScalePointX = detector.focusX
            mScalePointY = detector.focusY
            mScaleFactor = max(mMinZoom, min(mScaleFactor, mMaxZoom))
            invalidate()
            return true
        }
    }

    fun resetCamera() {
        mScaleFactor = 1f
        mScalePointX = 0f
        mScalePointY = 0f

        mPositionX = 0f
        mPositionY = 50f
        mLastTouchX = 0f
        mLastTouchY = 0f
        invalidate()
    }

    fun setPoints(newPoints: List<WorldPoint>) {
        mPoints = newPoints
        invalidate()
    }

    companion object {
        const val DOT_RADIUS = 0.5f
    }
}