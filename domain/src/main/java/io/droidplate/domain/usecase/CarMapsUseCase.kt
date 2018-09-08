package io.droidplate.domain.usecase

import io.droidplate.domain.model.Car
import io.droidplate.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

class CarMapsUseCase @Inject constructor(val carRepository: CarRepository){

    fun getCars(): Single<List<Car>> = carRepository.fetchCarsForMap()
}