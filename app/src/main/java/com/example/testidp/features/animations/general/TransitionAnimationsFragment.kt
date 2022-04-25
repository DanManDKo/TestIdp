package com.example.testidp.features.animations.general

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.testidp.R
import com.example.testidp.base.BaseFragment

class TransitionAnimationsFragment : BaseFragment(R.layout.fragment_transition_animations) {
    companion object {
        fun newInstance() = TransitionAnimationsFragment()
    }

    private var cvAndroid: CardView? = null
    private var ivAndroid: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        cvAndroid = view.findViewById(R.id.cvAndroid)
        ivAndroid = view.findViewById(R.id.ivAndroid)

        cvAndroid?.setOnClickListener {
            animate()
        }
    }

    private fun animate() {
//        TransitionManager.beginDelayedTransition()
    }
}