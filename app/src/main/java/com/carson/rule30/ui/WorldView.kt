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
import com.carson.rule30.R
import com.carson.rule30.data.models.WorldPoint
import kotlin.math.max
import kotlin.math.min


class WorldView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var scaleDetector: ScaleGestureDetector? = null
    var scaleFactor = 1f
    var scalePointX = 0f
    var scalePointY = 0f

    private var mPositionX = 0f
    private var mPositionY = 50f
    private var mLastTouchX = 0f
    private var mLastTouchY = 0f
    private val mMinZoom = 0.5f
    private val mMaxZoom = 5.0f

    private val paint = Paint()

    private var points: List<WorldPoint> = arrayListOf()

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
        paint.color = ContextCompat.getColor(context, android.R.color.black)
        paint.style = Paint.Style.FILL
        paint.textSize = context.resources.getDimension(R.dimen.text_size)
        paint.isAntiAlias = true

        scaleDetector = ScaleGestureDetector(context, ScaleListener())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        // Scale canvas to the scale factor and scale point.
        canvas.scale(scaleFactor, scaleFactor, scalePointX, scalePointY)
        // Translate the canvas to the saved position.
        canvas.translate(mPositionX, mPositionY)

        // Draw a circle for every point in the points array.
        for (point in points) {
            canvas.drawCircle(
                point.xCoord,
                point.yCoord,
                point.radius,
                paint
            )
        }

        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //the scale gesture detector should inspect all the touch events
        scaleDetector?.onTouchEvent(event)
        val action = event.action
        when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                //get x and y cords of where we touch the screen
                val x = (event.x - scalePointX) / scaleFactor
                val y = (event.y - scalePointY) / scaleFactor

                //remember where touch event started
                mLastTouchX = x
                mLastTouchY = y

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (event.x - scalePointX) / scaleFactor
                val y = (event.y - scalePointY) / scaleFactor
                if (scaleDetector?.isInProgress == false) {
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
            scaleFactor *= detector.scaleFactor
            scalePointX = detector.focusX
            scalePointY = detector.focusY
            scaleFactor = max(mMinZoom, min(scaleFactor, mMaxZoom))
            invalidate()
            return true
        }
    }

    fun resetCamera() {
        scaleFactor = 1f
        scalePointX = 0f
        scalePointY = 0f

        mPositionX = 0f
        mPositionY = 50f
        mLastTouchX = 0f
        mLastTouchY = 0f
        invalidate()
    }

    fun setPoints(newPoints: List<WorldPoint>) {
        points = newPoints
        invalidate()
    }

    companion object {
        const val DOT_RADIUS = 0.5f
    }
}