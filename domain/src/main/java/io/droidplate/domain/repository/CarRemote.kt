package io.droidplate.domain.repository


import io.droidplate.domain.model.Car
import io.reactivex.Single

interface CarRemote {
    fun fetchAllCars(): Single<List<Car>>
}