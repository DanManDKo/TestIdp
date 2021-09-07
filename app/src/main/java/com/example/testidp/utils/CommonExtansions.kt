package com.example.testidp.utils

import android.os.Bundle

fun Bundle.put(key: String, value: Any) {
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Float -> putFloat(key, value)
        else -> throw IllegalArgumentException("$value is nit supported yet")
    }
}