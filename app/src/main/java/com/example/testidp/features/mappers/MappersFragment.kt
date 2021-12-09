package com.example.testidp.features.mappers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testidp.R
import com.example.testidp.base.BaseMVVMFragment

class MappersFragment private constructor() : BaseMVVMFragment<MappersViewModel>(
    R.layout.fragment_mappers,
    MappersViewModel::class
) {

    companion object {
        fun newInstance() = MappersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mappers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userLD.observe {
            it.toString()
        }
    }
}