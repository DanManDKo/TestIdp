package com.example.testidp.utils

fun String.capFirst(): String {
    return replaceFirst(this.first(), this.first().uppercaseChar())
}

fun String.normalize(): String {
    return lowercase().capFirst()
}