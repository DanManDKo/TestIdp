package com.example.testidp.features.animations.general

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.testidp.R
import com.example.testidp.base.BaseFragment

class VectorDrawableAnimationFragment : BaseFragment(R.layout.fragment_vector_drawable_animations) {

    companion object {
        fun newInstance() = VectorDrawableAnimationFragment()
    }

    private var ivAnimated: ImageView? = null
    private var btn: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        ivAnimated = view.findViewById(R.id.ivAnimated)
        ivAnimated?.isEnabled = false
        btn = view.findViewById(R.id.btn)

        btn?.setOnClickListener {
            val wasEnabled = ivAnimated?.isEnabled ?: false
            ivAnimated?.isEnabled = !wasEnabled
        }
    }
}