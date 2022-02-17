package com.example.testidp.features.animations.general

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.PathInterpolator
import android.widget.ImageView
import com.example.testidp.R
import com.example.testidp.base.BaseFragment

class PathAnimationFragment : BaseFragment(R.layout.fragmetn_path_animation) {

    companion object {
        fun newInstance() = PathAnimationFragment()
    }

    private var ivBall: ImageView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        ivBall = view.findViewById(R.id.ivBall)

        ivBall?.setOnClickListener {
            applyAnimation()
        }
    }

    private fun applyAnimation() {
        ivBall?.let { view ->

            val path = Path().apply {
                arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
            }

            val pathInterpolator = PathInterpolator(0f, 0f, 1f, 1f)
            ObjectAnimator.ofFloat(view, "translationX", "translationY", path).apply {
                duration = 2000
                interpolator = pathInterpolator
                start()
            }
        }
    }
}