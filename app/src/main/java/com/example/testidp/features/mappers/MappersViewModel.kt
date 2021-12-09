package com.example.testidp.features.mappers

import androidx.lifecycle.MutableLiveData
import com.example.testidp.base.BaseViewModel

class MappersViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val userLD = MutableLiveData<User>()

    init {
        getUser()
    }

    private fun getUser() {
        userLD.value = userRepository.getUser()
    }
}