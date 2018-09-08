package io.droidplate.data.cache

import io.droidplate.data.model.CarEntity
import io.droidplate.domain.model.Car
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CarCache {
    fun insertCars(cars: List<CarEntity>): Completable
    fun getAllCars(): Single<List<CarEntity>>
}