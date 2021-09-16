package com.example.testidp.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

open class BaseMVVMFragment<VIEW_MODEL : BaseViewModel>(
    @LayoutRes private val resId: Int,
    viewModelClass: KClass<VIEW_MODEL>
) : BaseFragment(resId) {

    protected val viewModel: VIEW_MODEL by viewModel(clazz = viewModelClass)

    private fun baseObserve() {
        viewModel.progressLiveData.observe {

        }
    }

    inline fun <reified T> LiveData<T>.observe(observer: Observer<T>) {
        observe(this@BaseMVVMFragment.viewLifecycleOwner, observer)
    }
}