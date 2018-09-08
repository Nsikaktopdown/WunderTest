package io.droidplate.domain.usecase

import io.droidplate.domain.model.Car
import io.droidplate.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

class CarsUserCase @Inject constructor(val carRepository: CarRepository){

    fun getCars(refresh: Boolean): Single<List<Car>> = carRepository.getCars(refresh)
}