package io.droidplate.domain.repository

import io.droidplate.domain.model.Car
import io.reactivex.Single

interface CarRepository {
    fun saveCarsToDb(): Single<List<Car>>
    fun getCars(refresh: Boolean): Single<List<Car>>
    fun fetchCarsForMap(): Single<List<Car>>

}