package com.example.testidp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val progressLiveData = MutableLiveData<Boolean>()
}