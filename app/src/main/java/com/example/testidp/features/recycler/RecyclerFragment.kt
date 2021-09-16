package com.example.testidp.features.recycler

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.base.BaseMVVMFragment
import com.example.testidp.features.recycler.drag.DragAndDropAdapter

class RecyclerFragment private constructor() : BaseMVVMFragment<RecyclerFragmentViewModel>(
    R.layout.fragment_recycler,
    RecyclerFragmentViewModel::class
) {

    companion object {
        fun newInstance() = RecyclerFragment()
    }

    private var recycler: RecyclerView? = null
    private var dragAndDropAdapter: DragAndDropAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
    }

    private fun setupUi(view: View) {
        recycler = view.findViewById(R.id.recycler)
        dragAndDropAdapter = DragAndDropAdapter()
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.adapter = dragAndDropAdapter
    }
}