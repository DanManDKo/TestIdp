package com.example.testidp.utils

fun String.underline(): Char = '_'
fun String.space(): Char = ' '


fun String.capFirst(): String {
    return replaceFirst(this.first(), this.first().uppercaseChar())
}

fun String.normalize(): String {
    return lowercase().capFirst().replace(underline(), space())
}


