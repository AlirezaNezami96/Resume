package alireza.nezami.resume.presentation.utils

import alireza.nezami.resume.R
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * Created by Alireza Nezami on 3/27/2022.
 */
class StepIndicator : View {
    private var radius = 0
    private var mLineHeight = 0f
    private var strokeWidth = 0
    private var currentStepPosition = 0
    private var stepsCount = 1
    private var customBackgroundColor = 0
    private var stepColor = 0
    private var currentColor = 0
    private var textColor = 0
    private var secondaryTextColor = 0
    private var centerY = 0
    private var startX = 0
    private var endX = 0
    private var stepDistance = 0
    private var offset = 0f
    private var offsetPixel = 0
    private var pagerScrollState = 0
    private lateinit var paint: Paint
    private lateinit var pStoke: Paint
    private lateinit var pText: Paint
    private val textBounds: Rect = Rect()
    private var onClickListener: OnClickListener? = null
    private val hsvCurrent = FloatArray(3)
    private val hsvBG = FloatArray(3)
    private val hsvProgress = FloatArray(3)
    private var clickable = true
    private var withViewpager = false
    private var viewPagerChangeListener: ViewPagerOnChangeListener? = null
    private var disablePageChange = false

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        initAttributes(context, attributeSet)
        paint = Paint()
        pStoke = Paint()
        pText = Paint()
        paint.color = stepColor
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.strokeWidth = mLineHeight
        pStoke.color = stepColor
        pStoke.strokeWidth = strokeWidth.toFloat()
        pStoke.style = Paint.Style.STROKE
        pStoke.flags = Paint.ANTI_ALIAS_FLAG
        pText.color = textColor
        pText.textSize = radius.toFloat()
        pText.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        pText.textAlign = Paint.Align.CENTER
        pText.flags = Paint.ANTI_ALIAS_FLAG
        minimumHeight = radius * 3
        Color.colorToHSV(currentColor, hsvCurrent)
        Color.colorToHSV(customBackgroundColor, hsvBG)
        Color.colorToHSV(stepColor, hsvProgress)
        invalidate()
    }

    private fun initAttributes(context: Context, attributeSet: AttributeSet?) {
        val attr: TypedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.StepIndicator, 0, 0)
        try {
            radius = attr.getDimension(
                R.styleable.StepIndicator_siRadius,
                dp2px(DEFAULT_STEP_RADIUS.toFloat())
            )
                .toInt()
            strokeWidth = attr.getDimension(
                R.styleable.StepIndicator_siStrokeWidth, dp2px(
                    DEFAULT_STOKE_WIDTH.toFloat()
                )
            )
                .toInt()
            stepsCount = attr.getInt(R.styleable.StepIndicator_siStepCount, DEFAULT_STEP_COUNT)
            mLineHeight =
                attr.getDimension(R.styleable.StepIndicator_siLineHeight, DEFAULT_LINE_HEIGHT)
            stepColor = attr.getColor(
                R.styleable.StepIndicator_siStepColor,
                ContextCompat.getColor(context, DEFAULT_STEP_COLOR)
            )
            currentColor = attr.getColor(
                R.styleable.StepIndicator_siCurrentStepColor,
                ContextCompat.getColor(context, DEFAULT_CURRENT_STEP_COLOR)
            )
            customBackgroundColor = attr.getColor(
                R.styleable.StepIndicator_siBackgroundColor,
                ContextCompat.getColor(context, DEFAULT_BACKGROUND_COLOR)
            )
            textColor = attr.getColor(
                R.styleable.StepIndicator_siTextColor,
                ContextCompat.getColor(context, DEFAULT_TEXT_COLOR)
            )
            secondaryTextColor = attr.getColor(
                R.styleable.StepIndicator_siSecondaryTextColor,
                ContextCompat.getColor(context, DEFAULT_SECONDARY_TEXT_COLOR)
            )
        } finally {
            attr.recycle()
        }
    }

    @SuppressLint("NewApi")
    protected fun dp2px(dp: Float): Float {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))
            .toFloat()
    }

    fun getStepsCount(): Int {
        return stepsCount
    }

    private fun setStepsCount(stepsCount: Int) {
        this.stepsCount = stepsCount
        invalidate()
    }

    private fun getCurrentStepPosition(): Int {
        return currentStepPosition
    }

    fun setCurrentStepPosition(currentStepPosition: Int) {
        this.currentStepPosition = currentStepPosition
        invalidate()
    }

    override fun isClickable(): Boolean {
        return clickable
    }

    override fun setClickable(clickable: Boolean) {
        this.clickable = clickable
    }

    fun getRadius(): Int {
        return radius
    }

    fun setRadius(radius: Int) {
        this.radius = radius
    }

    fun setupWithViewPager(viewPager: ViewPager) {
        val adapter = viewPager.adapter
            ?: throw IllegalArgumentException("ViewPager does not have a PagerAdapter set")
        if (viewPagerChangeListener == null) {
            viewPagerChangeListener = ViewPagerOnChangeListener(this)
        }
        withViewpager = true
        // First we'll add Steps.
        setStepsCount(adapter.count)

        // Now we'll add our page change listener to the ViewPager
        viewPager.addOnPageChangeListener(viewPagerChangeListener!!)

        // Now we'll add a selected listener to set ViewPager's currentStepPosition item
        setOnClickListener(ViewPagerOnSelectedListener(viewPager))
        viewPager.setOnTouchListener { v, event ->
            if (event.actionMasked == MotionEvent.ACTION_MOVE) {
                (v as ViewPager).addOnPageChangeListener(viewPagerChangeListener!!)
                disablePageChange = false
            }
            false
        }

        // Make sure we reflect the currently set ViewPager item
        if (adapter.count > 0) {
            val curItem = viewPager.currentItem
            if (getCurrentStepPosition() != curItem) {
                setCurrentStepPosition(curItem)
                invalidate()
            }
        }
    }

    protected override fun onDraw(canvas: Canvas) {
        if (stepsCount <= 1) {
            visibility = GONE
            return
        }
        super.onDraw(canvas)
        var pointX = startX
        val pointOffset: Int
        /** draw Line  */
        for (i in 0 until stepsCount - 1) {
            if (i < currentStepPosition) {
                paint.color = stepColor
                canvas.drawLine(
                    pointX.toFloat(),
                    centerY.toFloat(), (pointX + stepDistance).toFloat(), centerY.toFloat(), paint
                )
            } else if (i == currentStepPosition) {
                paint.color = customBackgroundColor
                canvas.drawLine(
                    pointX.toFloat(), centerY.toFloat(), (pointX + stepDistance).toFloat(),
                    centerY.toFloat(), paint
                )
            } else {
                paint.color = customBackgroundColor
                canvas.drawLine(
                    pointX.toFloat(),
                    centerY.toFloat(), (pointX + stepDistance).toFloat(), centerY.toFloat(), paint
                )
            }
            pointX = pointX + stepDistance
        }
        /**draw progress Line   */
        if (offsetPixel != 0 && pagerScrollState == 1) {
            pointOffset = startX + currentStepPosition * stepDistance
            val drawOffset = pointOffset + offsetPixel
            if (drawOffset >= startX && drawOffset <= endX) {
                if (offsetPixel < 0) {
                    paint.color = customBackgroundColor
                } else {
                    paint.color = stepColor
                }
                canvas.drawLine(
                    pointOffset.toFloat(),
                    centerY.toFloat(), drawOffset.toFloat(), centerY.toFloat(), paint
                )
            }
        }
        /**draw Circle  */
        pointX = startX
        for (i in 0 until stepsCount) {
            if (i < currentStepPosition) {
                //draw previous step
                paint.color = stepColor
                canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)

                //draw transition
                if (i == currentStepPosition - 1 && offsetPixel < 0 && pagerScrollState == 1) {
                    pStoke.alpha = 255
                    pStoke.strokeWidth = (strokeWidth - Math.round(strokeWidth * offset)).toFloat()
                    canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), pStoke)
                }
                pText.color = secondaryTextColor
            } else if (i == currentStepPosition) {
                //draw current step
                if (offsetPixel == 0 || pagerScrollState == 0) {
                    //set stroke default
                    paint.color = currentColor
                    pStoke.strokeWidth = Math.round(strokeWidth.toFloat()).toFloat()
                    pStoke.alpha = 255
                } else if (offsetPixel < 0) {
                    pStoke.strokeWidth = Math.round(strokeWidth * offset).toFloat()
                    pStoke.alpha = Math.round(offset * 255f)
                    paint.color = getColorToBG(offset)
                } else {
                    //set stroke transition
                    paint.color = getColorToProgress(offset)
                    pStoke.strokeWidth = (strokeWidth - Math.round(strokeWidth * offset)).toFloat()
                    pStoke.alpha = 255 - Math.round(offset * 255f)
                }
                canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)
                canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), pStoke)
                pText.color = textColor
            } else {
                //draw next step
                paint.color = customBackgroundColor
                canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)
                pText.color = secondaryTextColor

                //draw transition
                if (i == currentStepPosition + 1 && offsetPixel > 0 && pagerScrollState == 1) {
                    pStoke.strokeWidth = (strokeWidth * offset).roundToInt().toFloat()
                    pStoke.alpha = (offset * 255f).roundToInt()
                    canvas.drawCircle(pointX.toFloat(), centerY.toFloat(), radius.toFloat(), pStoke)
                }
            }
            drawTextCentred(canvas, pText, (i + 1).toString(), pointX.toFloat(), centerY.toFloat())
            pointX += stepDistance
        }
    }

    private fun drawTextCentred(canvas: Canvas, paint: Paint?, text: String, cx: Float, cy: Float) {
        paint?.getTextBounds(text, 0, text.length, textBounds)
        if (paint != null) {
            canvas.drawText(text, cx, cy - textBounds.exactCenterY(), paint)
        }
    }

    private fun getColorToBG(offset: Float): Int {
        var offset = offset
        offset = abs(offset)
        val hsv = FloatArray(3)
        hsv[0] = hsvBG[0] + (hsvCurrent[0] - hsvBG[0]) * offset
        hsv[1] = hsvBG[1] + (hsvCurrent[1] - hsvBG[1]) * offset
        hsv[2] = hsvBG[2] + (hsvCurrent[2] - hsvBG[2]) * offset
        return Color.HSVToColor(hsv)
    }

    private fun getColorToProgress(offset: Float): Int {
        var offset = offset
        offset = abs(offset)
        val hsv = FloatArray(3)
        hsv[0] = hsvCurrent[0] + (hsvProgress[0] - hsvCurrent[0]) * offset
        hsv[1] = hsvCurrent[1] + (hsvProgress[1] - hsvCurrent[1]) * offset
        hsv[2] = hsvCurrent[2] + (hsvProgress[2] - hsvCurrent[2]) * offset
        return Color.HSVToColor(hsv)
    }

    private fun setOffset(offset: Float, position: Int) {
        this.offset = offset
        offsetPixel = Math.round(stepDistance * offset)
        if (currentStepPosition > position) {
            offsetPixel = offsetPixel - stepDistance
        } else {
            currentStepPosition = position
        }
        invalidate()
    }

    private fun setPagerScrollState(pagerScrollState: Int) {
        this.pagerScrollState = pagerScrollState
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!clickable) return super.onTouchEvent(event)
        var pointX = startX
        val xTouch: Int
        val yTouch: Int
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                xTouch = event.getX(0).toInt()
                yTouch = event.getY(0).toInt()
                var i = 0
                while (i < stepsCount) {
                    if (Math.abs(xTouch - pointX) < radius + 5 && Math.abs(yTouch - centerY) < radius + 5) {
                        if (!withViewpager) {
                            setCurrentStepPosition(i)
                        }
                        if (onClickListener != null) {
                            onClickListener!!.onClick(i)
                        }
                    }
                    pointX += stepDistance
                    i++
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, radius * 3)
        centerY = height / 2
        startX = radius * 2
        endX = width - radius * 2
        stepDistance = (endX - startX) / (stepsCount - 1)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerY = height / 2
        startX = radius * 2
        endX = width - radius * 2
        stepDistance = (endX - startX) / (stepsCount - 1)
        invalidate()
    }

    inner class ViewPagerOnChangeListener(private val stepIndicator: StepIndicator) :
        OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (!disablePageChange) {
                stepIndicator.setOffset(positionOffset, position)
            }
        }

        override fun onPageSelected(position: Int) {
            if (!disablePageChange) {
                stepIndicator.setCurrentStepPosition(position)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            stepIndicator.setPagerScrollState(state)
        }
    }

    inner class ViewPagerOnSelectedListener(private val mViewPager: ViewPager) :
        OnClickListener {
        override fun onClick(position: Int) {
            disablePageChange = true
            setCurrentStepPosition(position)
            mViewPager.currentItem = position
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState: Parcelable? = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.mLineHeight = mLineHeight
        ss.radius = radius
        ss.strokeWidth = strokeWidth
        ss.currentStepPosition = currentStepPosition
        ss.stepsCount = stepsCount
        ss.backgroundColor = customBackgroundColor
        ss.stepColor = stepColor
        ss.currentColor = currentColor
        ss.textColor = textColor
        ss.secondaryTextColor = secondaryTextColor
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }
        val ss = state
        super.onRestoreInstanceState(ss.superState)
        mLineHeight = ss.mLineHeight
        radius = ss.radius
        strokeWidth = ss.strokeWidth
        currentStepPosition = ss.currentStepPosition
        stepsCount = ss.stepsCount
        customBackgroundColor = ss.backgroundColor
        stepColor = ss.stepColor
        currentColor = ss.currentColor
        textColor = ss.textColor
        secondaryTextColor = ss.secondaryTextColor
    }

    internal class SavedState : BaseSavedState {
        var radius = 0
        var mLineHeight = 0f
        var strokeWidth = 0
        var currentStepPosition = 0
        var stepsCount = 0
        var backgroundColor = 0
        var stepColor = 0
        var currentColor = 0
        var textColor = 0
        var secondaryTextColor = 0

        constructor(superState: Parcelable?) : super(superState)
        private constructor(`in`: Parcel) : super(`in`) {
            mLineHeight = `in`.readFloat()
            radius = `in`.readInt()
            strokeWidth = `in`.readInt()
            currentStepPosition = `in`.readInt()
            stepsCount = `in`.readInt()
            backgroundColor = `in`.readInt()
            stepColor = `in`.readInt()
            currentColor = `in`.readInt()
            textColor = `in`.readInt()
            secondaryTextColor = `in`.readInt()
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeFloat(mLineHeight)
            dest.writeInt(radius)
            dest.writeInt(strokeWidth)
            dest.writeInt(currentStepPosition)
            dest.writeInt(stepsCount)
            dest.writeInt(backgroundColor)
            dest.writeInt(stepColor)
            dest.writeInt(currentColor)
            dest.writeInt(textColor)
            dest.writeInt(secondaryTextColor)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState?> =
                object : Parcelable.Creator<SavedState?> {
                    override fun createFromParcel(`in`: Parcel): SavedState? {
                        return SavedState(`in`)
                    }

                    override fun newArray(size: Int): Array<SavedState?> {
                        return arrayOfNulls(size)
                    }
                }
        }
    }

    companion object {
        private const val DEFAULT_STEP_RADIUS = 16 //DP
        private const val DEFAULT_STOKE_WIDTH = 6 //DP
        private const val DEFAULT_STEP_COUNT = 4 //DP
        private val DEFAULT_BACKGROUND_COLOR: Int = R.color.background_default
        private val DEFAULT_STEP_COLOR: Int = R.color.step_default
        private val DEFAULT_CURRENT_STEP_COLOR: Int = R.color.current_step_default
        private val DEFAULT_TEXT_COLOR: Int = R.color.text_default
        private val DEFAULT_SECONDARY_TEXT_COLOR: Int = R.color.secondary_text_default
        private const val DEFAULT_LINE_HEIGHT = 6.0f
    }
}