package com.example.testidp.utils.delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CustomDelegate : ReadWriteProperty<Any, String> {

    private var result = ""

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return result
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        val text = if (value.isBlank()) "Default text" else value
        result = "The inserted text: $text" +
                "\n------\nThe calling object: $thisRef" +
                "\n------\nThe property name: ${property.name}"
    }
}