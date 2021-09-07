package com.example.testidp.features.delegate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import com.example.testidp.utils.click
import com.example.testidp.utils.delegates.CustomDelegate
import com.example.testidp.utils.delegates.FragmentArgumentsDelegate

class CustomDelegateFragment private constructor() :
    BaseFragment(R.layout.fragment_custon_delegate) {

    companion object {

        fun newInstance(
            stringArg: String = "Arg",
            intArg: Int = 666,
            floatArg: Float = 3.14f
        ): CustomDelegateFragment {
            return CustomDelegateFragment().apply {
                this.stringArg = stringArg
                this.intArg = intArg
                this.floatArg = floatArg
            }
        }
    }

    private var stringArg: String by FragmentArgumentsDelegate()
    private var intArg: Int by FragmentArgumentsDelegate()
    private var floatArg: Float by FragmentArgumentsDelegate()

    private var btnAction: Button? = null
    private var tvResult: TextView? = null
    private var etContent: EditText? = null
    private var tvStringArg: TextView? = null
    private var tvIntArg: TextView? = null
    private var tvFloatArg: TextView? = null

    private var delegateProcessedText: String by CustomDelegate()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
        tvStringArg?.text = stringArg
        tvIntArg?.text = intArg.toString()
        tvFloatArg?.text = floatArg.toString()
    }

    private fun setupUi(view: View) {
        bindViews(view)
    }

    private fun bindViews(view: View) {
        btnAction = view.findViewById(R.id.btnAction)
        btnAction?.click {
            etContent?.text?.let {
                processInputText(it.toString())
                tvResult?.text = delegateProcessedText
            }
        }

        tvResult = view.findViewById(R.id.tvResult)
        etContent = view.findViewById(R.id.etContent)
        tvStringArg = view.findViewById(R.id.tvStringArg)
        tvIntArg = view.findViewById(R.id.tvIntArg)
        tvFloatArg = view.findViewById(R.id.tvFloatArg)
    }

    private fun processInputText(text: String) {
        delegateProcessedText = text
    }
}