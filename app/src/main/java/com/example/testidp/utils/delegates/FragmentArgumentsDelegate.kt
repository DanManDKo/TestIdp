package com.example.testidp.utils.delegates

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testidp.utils.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentArgumentsDelegate<T : Any> : ReadWriteProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return thisRef.arguments?.get(property.name) as? T
            ?: throw IllegalArgumentException("Value for key ${property.name} cannot be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = if (thisRef.arguments == null) {
            thisRef.arguments = Bundle()
            thisRef.arguments
        } else {
            thisRef.arguments
        }
        val key = property.name
        args?.put(key, value)
    }
}