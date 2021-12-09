package com.example.testidp.features.mappers

class MapperDelegate(val mappers: Map<MapperKey, Mapper<*, *>>) {


    @Suppress("UNCHECKED_CAST")
    inline fun <reified From, reified To> map(input: From): To {

        val mapper = mappers[MapperKey(From::class.java, To::class.java)]

        return if (
            mapper?.sourceClass?.isAssignableFrom(From::class.java) == true
            && mapper.resultClass == To::class.java
        ) {
            (mapper as Mapper<From, To>).map(input, this)
        } else {
            throw IllegalArgumentException(
                "Mapper with source ${From::class.java.name}" +
                        " and target ${To::class.java.name} is not exist"
            )
        }
    }

    data class MapperKey(val clazzFrom: Class<*>, val clazzTo: Class<*>)

    class Builder {

        private val mappers: MutableMap<MapperKey, Mapper<*, *>> = mutableMapOf()

        fun <Source, Result> registerMapper(mapper: Mapper<Source, Result>): Builder {
            mappers[MapperKey(mapper.sourceClass, mapper.resultClass)] = mapper

            return this
        }

        fun registerMapper(list: List<Mapper<*, *>>): Builder {
            mappers.putAll(
                list.map { mapper ->
                    MapperKey(mapper.sourceClass, mapper.resultClass) to mapper
                }
            )
            return this
        }

        fun build(): MapperDelegate {
            return MapperDelegate(mappers)
        }
    }

}

