package com.example.testidp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.click(clickEvent: (View) -> Unit) {
    setOnClickListener {
        clickEvent.invoke(it)
    }
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attach)
}