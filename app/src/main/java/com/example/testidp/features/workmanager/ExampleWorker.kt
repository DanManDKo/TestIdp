package com.example.testidp.features.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class ExampleWorker(context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    companion object {
        private const val TAG = "ExampleWorker"
        private var retryCounter: Int = 0
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        val data = params.inputData
        Log.d(TAG, "doWork start")
        Log.d(TAG, "doWork data : $data")
        TimeUnit.SECONDS.sleep(5)
        Log.d(TAG, "doWork end")
        val result = Data(mapOf("OLOLO" to 666))
        return if (retryCounter == 0) {
            retryCounter++
            Result.Retry()
        } else {
            Result.Success(result)
        }

    }
}