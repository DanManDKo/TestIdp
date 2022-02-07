package com.example.testidp.features.workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.work.*
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import java.util.*

class WorkManagerFragment : BaseFragment(R.layout.fragment_work_manager) {
    companion object {
        fun newInstance() = WorkManagerFragment()
    }

    private var btnStartExample: Button? = null
    private var tvWorkInfo: TextView? = null
    private var btnCancelCurrent: Button? = null
    private var btnRunCharging: Button? = null

    private var currentRequest: WorkRequest? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        tvWorkInfo = view.findViewById(R.id.tvWorkInfo)
        btnCancelCurrent = view.findViewById(R.id.btnCancelCurrent)
        btnStartExample = view.findViewById(R.id.btnStartExample)
        btnRunCharging = view.findViewById(R.id.btnRunCharging)

        tvWorkInfo?.setOnLongClickListener {
            tvWorkInfo?.text = null
            true
        }
        btnStartExample?.setOnClickListener { startExampleWorker() }
        btnCancelCurrent?.setOnClickListener {
            currentRequest?.let {
                WorkManager.getInstance(requireContext()).cancelWorkById(it.id)
            }
        }

        btnRunCharging?.setOnClickListener {
            startCharging()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun startExampleWorker() {
        val inputData = Data(mapOf("Banan" to "Negr"))
        currentRequest = OneTimeWorkRequestBuilder<ExampleWorker>()
            .setInputData(inputData)
            .build()
        currentRequest?.let {
            observeWorkStatuses(it.id)
            WorkManager.getInstance(requireContext()).enqueue(it)
        }

    }

    @SuppressLint("RestrictedApi")
    private fun startCharging() {
        val inputData = Data(mapOf("Banan" to "Negr"))

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        currentRequest = OneTimeWorkRequestBuilder<ExampleWorker>()
            .setInputData(inputData)
            .setConstraints(constraints)
            .build()
        currentRequest?.let {
            observeWorkStatuses(it.id)
            WorkManager.getInstance(requireContext()).enqueue(it)
        }
    }

    private fun observeWorkStatuses(uuid: UUID) {
        with(WorkManager.getInstance(requireContext())) {
            getWorkInfoByIdLiveData(uuid).observe { info ->
                tvWorkInfo?.let {
                    val prevText = it.text.toString()
                    it.text = null
                    val newText = prevText + "\n\n${info.state}" + " ${info.outputData}"
                    it.text = newText
                }
            }
        }
    }

}