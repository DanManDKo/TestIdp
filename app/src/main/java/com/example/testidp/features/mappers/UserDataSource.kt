package com.example.testidp.features.mappers

import com.example.testidp.features.mappers.response.UserResponse

interface UserDataSource {
    fun getUser(): UserResponse
}