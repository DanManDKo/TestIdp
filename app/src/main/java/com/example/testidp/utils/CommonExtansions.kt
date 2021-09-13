package com.example.testidp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Bundle.put(key: String, value: Any) {
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Float -> putFloat(key, value)
        else -> throw IllegalArgumentException("$value is nit supported yet")
    }
}
