package com.example.testidp.features.mappers

import com.example.testidp.features.mappers.response.CarResponse
import com.example.testidp.features.mappers.response.DogResponse
import com.example.testidp.features.mappers.response.UserResponse

class UserDataSourceImpl : UserDataSource {
    override fun getUser(): UserResponse {

        val car = CarResponse(
            type = "vedro",
            price = 100500.00,
            color = 11111
        )
        val dog = DogResponse(
            name = "Tuzik",
            type = "Sabaka",
            age = "8"
        )
        return UserResponse(
            name = "Vasyan",
            age = 33,
            dogResponse = dog,
            carResponse = car
        )
    }
}