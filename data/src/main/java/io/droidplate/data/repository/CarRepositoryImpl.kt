package io.droidplate.data.repository

import io.droidplate.data.cache.CarCache
import io.droidplate.data.mapper.CarMapper
import io.droidplate.data.model.CarData
import io.droidplate.domain.model.Car
import io.droidplate.domain.repository.CarRemote
import io.droidplate.domain.repository.CarRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(var remote: CarRemote,
                                            var cache: CarCache,
                                            var mapper: CarMapper) : CarRepository {

    /**
     * Fetch cars from database
     */
    override fun fetchCarsForMap(): Single<List<Car>> = cache.getAllCars().map { mapper.mapEntityToDomain(it) }



    /**
     * Save cars to database
     */
    override fun saveCarsToDb(): Single<List<Car>> {
        return remote.fetchAllCars().doAfterSuccess {

                cache.insertCars(mapper.mapToEntity(it)).subscribe()
        }
    }

    override fun getCars(refresh: Boolean): Single<List<Car>> = when (refresh) {
        true -> saveCarsToDb().doOnSuccess { cache.getAllCars().map { mapper.mapEntityToDomain(it) } }
        false -> cache.getAllCars().map { mapper.mapEntityToDomain(it) }.doOnSuccess { Timber.d("fetched cars") }.onErrorResumeNext { getCars(true) }
    }


}