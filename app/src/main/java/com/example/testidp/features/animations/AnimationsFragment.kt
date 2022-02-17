package com.example.testidp.features.animations

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import com.example.testidp.features.animations.general.ObjectAnimationFragment
import com.example.testidp.features.animations.general.PathAnimationFragment
import com.example.testidp.features.animations.recycleranimations.RecyclerAnimationsFragment

class AnimationsFragment : BaseFragment(R.layout.fragment_animations) {

    companion object {
        fun newInstance() = AnimationsFragment()
    }

    private var btnRecyclerAnimations: Button? = null
    private var btnObjectAnimations: Button? = null
    private var btnPathAnimations: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(view)
    }

    private fun initButtons(view: View) {
        btnRecyclerAnimations = view.findViewById(R.id.btnRecyclerAnimations)
        btnObjectAnimations = view.findViewById(R.id.btnObjectAnimations)
        btnPathAnimations = view.findViewById(R.id.btnPathAnimations)

        btnRecyclerAnimations?.setOnClickListener {
            setFragment(RecyclerAnimationsFragment.newInstance())
        }

        btnObjectAnimations?.setOnClickListener {
            setFragment(ObjectAnimationFragment.newInstance())
        }

        btnPathAnimations?.setOnClickListener {
            setFragment(PathAnimationFragment.newInstance())
        }
    }
}