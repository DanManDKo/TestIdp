package com.example.testidp.features.animations.general

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.DecelerateInterpolator

class ObjectAnimationFragment : AnimationControlsFragment() {
    companion object {

        fun newInstance() = ObjectAnimationFragment()
    }


    override fun moveLeft() {
        val positionX = ivObject?.x ?: 0f
        val animationY = ObjectAnimator.ofFloat(ivObject, "x", positionX - MOVEMENT_STEP)
        animationY.duration = DURATION
        animationY.interpolator = DecelerateInterpolator()
        animationY.start()
    }

    override fun moveUp() {
        val positionX = ivObject?.y ?: 0f
        val animationY = ObjectAnimator.ofFloat(ivObject, "y", positionX - MOVEMENT_STEP)
        animationY.duration = DURATION
        animationY.interpolator = DecelerateInterpolator()
        animationY.start()
    }

    override fun moveRight() {
        val positionX = ivObject?.x ?: 0f
        val animationY = ObjectAnimator.ofFloat(ivObject, "x", positionX + MOVEMENT_STEP)
        animationY.duration = DURATION
        animationY.interpolator = DecelerateInterpolator()
        animationY.start()
    }

    override fun moveDown() {
        val positionX = ivObject?.y ?: 0f
        val animationY = ObjectAnimator.ofFloat(ivObject, "y", positionX + MOVEMENT_STEP)
        animationY.duration = DURATION
        animationY.interpolator = DecelerateInterpolator()
        animationY.start()
    }

    override fun addAlpha() {

    }

    override fun removeAlpha() {

    }

    override fun increase() {
        val scaleX = ivObject?.scaleX ?: 0f
        val scaleY = ivObject?.scaleY ?: 0f
        val animationWidth = ObjectAnimator.ofFloat(ivObject, "scaleX", scaleX + SCALE_STEP)
        val animationHeight = ObjectAnimator.ofFloat(ivObject, "scaleY", scaleY + SCALE_STEP)
        val set = AnimatorSet()
        set.play(animationWidth)
            .with(animationHeight)
        set.duration = DURATION
        set.interpolator = DecelerateInterpolator()
        set.start()
    }

    override fun decrease() {
        val scaleX = ivObject?.scaleX ?: 0f
        val scaleY = ivObject?.scaleY ?: 0f
        val animationWidth = ObjectAnimator.ofFloat(ivObject, "scaleX", scaleX - SCALE_STEP)
        val animationHeight = ObjectAnimator.ofFloat(ivObject, "scaleY", scaleY - SCALE_STEP)
        val set = AnimatorSet()
        set.play(animationWidth)
            .with(animationHeight)
        set.duration = DURATION
        set.interpolator = DecelerateInterpolator()
        set.start()
    }

    override fun cancelAnimation() {}
}