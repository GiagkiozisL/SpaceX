package com.mindera.spacex.ui.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.mindera.spacex.R
import java.lang.Exception

class AnimationFrameLayout : FrameLayout {
    private var frameSize = 0
    private var backDrawable: Drawable? = null
    private var innerPadding = 0
    private var type = 0
    private var autoStart = true
    private val animations = intArrayOf(R.drawable.animation_list_rocket)

    constructor(context: Context) : super(context) {
        startLoading(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.AnimationFrameLayout, 0, 0)
        try {
            frameSize = typedArray.getInt(R.styleable.AnimationFrameLayout_anim_frame_size, 230)
            backDrawable =
                typedArray.getDrawable(R.styleable.AnimationFrameLayout_anim_background_drawable)
            innerPadding =
                typedArray.getColor(R.styleable.AnimationFrameLayout_anim_inner_padding, 30)
            type = typedArray.getInt(R.styleable.AnimationFrameLayout_anim_type, 0)
            autoStart = typedArray.getBoolean(R.styleable.AnimationFrameLayout_auto_start, true)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }
        if (autoStart) {
            startLoading(context)
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        startLoading(context)
    }

    fun startLoading(ctx: Context?) {
        val drawableResource = getAnimationDrawable(type)
        val imageView = ImageView(ctx)
        val imageSide = frameSize - innerPadding
        val imgParams = LayoutParams(imageSide, imageSide)
        imgParams.gravity = Gravity.CENTER
        imageView.layoutParams = imgParams
        imageView.alpha = 0.8f
        imageView.setBackgroundResource(drawableResource)
        val animationDrawable = imageView.background as AnimationDrawable
        animationDrawable.start()
        val backImg = ImageView(ctx)
        backImg.setImageDrawable(backDrawable)
        val backParams = LayoutParams(frameSize, frameSize)
        backParams.gravity = Gravity.CENTER
        backImg.layoutParams = backParams
        addView(backImg)
        addView(imageView)
    }

    fun stopLoading() {
        removeAllViews()
    }

    fun initView(
        ctx: Context?,
        frameSize: Int,
        innerPadding: Int,
        type: Int,
        backDrawable: Drawable?,
        message: String?
    ) {
        val drawableResource = getAnimationDrawable(type)
        val imageView = ImageView(ctx)
        val imageSide = frameSize - innerPadding
        val imgParams = LayoutParams(imageSide, imageSide)
        imgParams.gravity = Gravity.CENTER
        imageView.layoutParams = imgParams
        //        imageView.setAlpha(0.4F);
        imageView.setBackgroundResource(drawableResource)
        val animationDrawable = imageView.background as AnimationDrawable
        animationDrawable.start()
        val backImg = ImageView(ctx)
        backImg.setImageDrawable(backDrawable)
        backImg.setBackgroundResource(R.drawable.round_rect_white)
        val backParams = LayoutParams(frameSize, frameSize)
        backParams.gravity = Gravity.CENTER
        backImg.layoutParams = backParams
        val cloudRight = ImageView(ctx)
        cloudRight.setImageResource(R.drawable.cloud)
        cloudRight.alpha = 0.8f
        val cloudR = LayoutParams(imageSide / 4, imageSide / 4)
        cloudR.gravity = Gravity.CENTER
        cloudRight.layoutParams = cloudR
        cloudR.setMargins(imageSide / 4 + 30, imageSide / 4 - 20, 0, 0)
        val cloudLeft = ImageView(ctx)
        cloudLeft.setImageResource(R.drawable.cloud)
        cloudLeft.alpha = 0.8f
        val cloudL = LayoutParams(imageSide / 4, imageSide / 4)
        cloudL.gravity = Gravity.CENTER
        cloudLeft.layoutParams = cloudL
        cloudL.setMargins(0, 0, imageSide / 4, imageSide / 4)
        val textView = AppCompatTextView(ctx!!)
        textView.text = message
        textView.textSize = 20f
        textView.setTextColor(ContextCompat.getColor(ctx, R.color.teal_200))
        textView.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        val txtParams = LayoutParams(frameSize, frameSize)
        txtParams.gravity = Gravity.CENTER
        txtParams.setMargins(0, 0, 0, 40)
        textView.layoutParams = txtParams
        addView(backImg)
        addView(imageView)
        addView(textView)
        addView(cloudLeft)
        addView(cloudRight)
        setBackgroundColor(ContextCompat.getColor(ctx, R.color.black_coral_trans))
        setOnTouchListener { view, motionEvent -> true }
    }

    private fun getAnimationDrawable(type: Int): Int {
        return animations[0]
    }
}