package com.example.testidp.features.animations

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import com.example.testidp.features.animations.general.ObjectAnimationFragment
import com.example.testidp.features.animations.recycleranimations.RecyclerAnimationsFragment

class AnimationsFragment : BaseFragment(R.layout.fragment_animations) {

    companion object {
        fun newInstance() = AnimationsFragment()
    }

    private var btnRecyclerAnimations: Button? = null
    private var btnPropertyAnimations: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(view)
    }

    private fun initButtons(view: View) {
        btnRecyclerAnimations = view.findViewById(R.id.btnRecyclerAnimations)
        btnPropertyAnimations = view.findViewById(R.id.btnPropertyAnimations)

        btnRecyclerAnimations?.setOnClickListener {
            setFragment(RecyclerAnimationsFragment.newInstance())
        }

        btnPropertyAnimations?.setOnClickListener {
            setFragment(ObjectAnimationFragment.newInstance())
        }
    }
}