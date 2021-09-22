package com.mindera.spacex.ui.view

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mindera.spacex.utils.DeviceUtils

open class BaseActivity : AppCompatActivity() {


    private lateinit var parentView: ViewGroup
    private lateinit var loadingProgressBar: AnimationFrameLayout

    fun setupLoadingView(view: ViewGroup, message: String) {
        val screen = DeviceUtils.getScreenSize(this@BaseActivity)
        val frame = Math.min(screen.first, screen.second)
        loadingProgressBar = createRocketView(frame / 2, message)
        parentView = view
    }

    fun showProgressAnimation() {
        // ensure bar has no parents
        loadingProgressBar.parent?.let {
            return
        }
        parentView.addView(loadingProgressBar)
    }

    fun hideProgressAnimation() {
        parentView.removeView(loadingProgressBar)
    }

    fun createRocketView(frameSize: Int, message: String): AnimationFrameLayout {
        val animationFrameLayout = AnimationFrameLayout(this@BaseActivity)
        animationFrameLayout.initView(this@BaseActivity, frameSize, 100, 0, null, message)
        return animationFrameLayout
    }
}