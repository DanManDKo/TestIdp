package com.example.testidp.features.animations.recycleranimations

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.base.BaseFragment

class RecyclerAnimationsFragment : BaseFragment(R.layout.fragment_recycler_animations) {
    companion object {
        fun newInstance() = RecyclerAnimationsFragment()
    }

    private var recycler: RecyclerView? = null
    private var animationsAdapter: AnimationsAdapter? = null

    private var btnAddMiddle: Button? = null
    private var btnAddEnd: Button? = null
    private var btnRemoveMiddle: Button? = null
    private var btnRemoveEnd: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        recycler = view.findViewById(R.id.recycler)
        animationsAdapter = AnimationsAdapter()
        recycler?.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animationsAdapter
            itemAnimator = CustomItemAnimator()
        }

        btnAddMiddle = view.findViewById(R.id.btnAddMiddle)
        btnAddEnd = view.findViewById(R.id.btnAddEnd)
        btnRemoveMiddle = view.findViewById(R.id.btnRemoveMiddle)
        btnRemoveEnd = view.findViewById(R.id.btnRemoveEnd)

        btnAddMiddle?.setOnClickListener {
            animationsAdapter?.addNewItemInTheMiddle()
        }

        btnAddMiddle?.setOnLongClickListener {
            animationsAdapter?.insertRange()
            true
        }

        btnAddEnd?.setOnClickListener {
            animationsAdapter?.addNewItemToTheEnd()
        }
        btnRemoveMiddle?.setOnClickListener {
            animationsAdapter?.removeItemFromTheMiddle()
        }
        btnRemoveMiddle?.setOnLongClickListener {
            animationsAdapter?.removeRange()
            true
        }

        btnRemoveEnd?.setOnClickListener {
            animationsAdapter?.removeItemFromTheEnd()
        }
    }
}