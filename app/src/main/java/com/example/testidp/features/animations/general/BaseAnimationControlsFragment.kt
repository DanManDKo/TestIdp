package com.example.testidp.features.animations.general

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.testidp.R
import com.example.testidp.base.BaseFragment

abstract class BaseAnimationControlsFragment : BaseFragment(R.layout.fragment_base_animations_control) {
    companion object {

        const val MOVEMENT_STEP = 100f
        const val SCALE_STEP = 1f
        const val DURATION = 1500L
    }


    private var btnUp: ImageButton? = null
    private var btnLeft: ImageButton? = null
    private var btnRight: ImageButton? = null
    private var btnDown: ImageButton? = null

    private var btnCancel: ImageButton? = null

    private var btnAddAlpha: Button? = null
    private var btnRemoveAlpha: Button? = null
    private var btnIncrease: Button? = null
    private var btnDecrease: Button? = null

    protected var ivObject: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        btnUp = view.findViewById(R.id.btnUp)
        btnLeft = view.findViewById(R.id.btnLeft)
        btnRight = view.findViewById(R.id.btnRight)
        btnDown = view.findViewById(R.id.btnDown)

        btnCancel = view.findViewById(R.id.btnCancel)

        btnAddAlpha = view.findViewById(R.id.btnAddAlpha)
        btnRemoveAlpha = view.findViewById(R.id.btnRemoveAlpha)
        btnIncrease = view.findViewById(R.id.btnIncrease)
        btnDecrease = view.findViewById(R.id.btnDecrease)

        ivObject = view.findViewById(R.id.ivObject)

        ivObject?.setOnClickListener {
            Toast.makeText(requireContext(), "Click on the image", Toast.LENGTH_SHORT).show()
        }

        btnUp?.setOnClickListener {
            moveUp()
        }

        btnLeft?.setOnClickListener {
            moveLeft()
        }

        btnRight?.setOnClickListener {
            moveRight()
        }

        btnDown?.setOnClickListener {
            moveDown()
        }

        btnAddAlpha?.setOnClickListener {
            addAlpha()
        }
        btnRemoveAlpha?.setOnClickListener {
            removeAlpha()
        }
        btnIncrease?.setOnClickListener {
            increase()
        }
        btnDecrease?.setOnClickListener {
            decrease()
        }

        btnCancel?.setOnClickListener {
            cancelAnimation()
        }
    }

    protected abstract fun moveUp()

    protected abstract fun moveLeft()

    protected abstract fun moveRight()

    protected abstract fun moveDown()

    protected abstract fun addAlpha()

    protected abstract fun removeAlpha()

    protected abstract fun increase()

    protected abstract fun decrease()

    protected abstract fun cancelAnimation()
}