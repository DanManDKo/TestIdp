package com.example.testidp.utils

import android.view.View

fun View.click(clickEvent: (View) -> Unit) {
    setOnClickListener {
        clickEvent.invoke(it)
    }
}