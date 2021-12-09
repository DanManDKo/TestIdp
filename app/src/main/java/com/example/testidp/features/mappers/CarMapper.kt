package com.example.testidp.features.mappers

import com.example.testidp.features.mappers.response.CarResponse

class CarMapper : Mapper<CarResponse, Car>(CarResponse::class.java, Car::class.java) {

    override fun map(input: CarResponse, delegate: MapperDelegate): Car {

        return Car(
            type = input.type,
            price = input.price,
            color = input.color
        )
    }

}