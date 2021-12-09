package com.example.testidp.features.mappers

import com.example.testidp.features.mappers.response.DogResponse

class DogMapper : Mapper<DogResponse, Dog>(DogResponse::class.java, Dog::class.java) {
    override fun map(input: DogResponse, delegate: MapperDelegate): Dog {
        return Dog(
            name = input.name,
            type = input.type,
            age = input.age
        )
    }
}