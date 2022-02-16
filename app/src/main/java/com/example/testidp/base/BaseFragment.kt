package com.example.testidp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.testidp.R
import com.example.testidp.container.ContainerActivity

open class BaseFragment(@LayoutRes val layout: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layout, container, false)


    inline fun <reified T> LiveData<T>.observe(observer: Observer<T>) {
        observe(this@BaseFragment.viewLifecycleOwner, observer)
    }

    fun setFragment(fragment: Fragment) {
        if (activity is ContainerActivity?) {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
        }
    }
}