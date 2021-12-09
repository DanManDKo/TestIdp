package com.example.testidp.features.mappers

abstract class Mapper<Source, Result>(
    val sourceClass: Class<Source>,
    val resultClass: Class<Result>
) {

    abstract fun map(input: Source, delegate: MapperDelegate): Result
}