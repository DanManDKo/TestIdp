package com.example.testidp.features.mappers.response

data class UserResponse(
    val name: String? = null,
    val age: Int? = null,
    val dogResponse: DogResponse? = null,
    val carResponse: CarResponse? = null
)
