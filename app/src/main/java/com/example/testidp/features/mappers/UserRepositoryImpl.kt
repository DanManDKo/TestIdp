package com.example.testidp.features.mappers

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val mapperDelegate: MapperDelegate
) : UserRepository {
    override fun getUser(): User {
        return mapperDelegate.map(userDataSource.getUser())
    }
}