package com.example.testidp.features.mappers

import com.example.testidp.features.mappers.response.UserResponse

class UserMapper() : Mapper<UserResponse, User>(UserResponse::class.java, User::class.java) {
    override fun map(input: UserResponse, delegate: MapperDelegate): User {
        return User(
            name = input.name,
            age = input.age,
            dog = input.dogResponse?.let { delegate.map(it) },
            car = input.carResponse?.let { delegate.map(it) }
        )
    }
}