package io.droidplate.data.repository

import io.droidplate.data.mapper.CarMapper
import io.droidplate.data.model.CarData
import io.droidplate.data.remote.RemoteApi
import io.droidplate.domain.model.Car
import io.droidplate.domain.repository.CarRemote
import io.reactivex.Single
import javax.inject.Inject

class CarRemoteImpl @Inject constructor(var api: RemoteApi, var mapper: CarMapper) : CarRemote {

    override fun fetchAllCars(): Single<List<Car>> {
        return api.getCars().map { mapper.mapToDomain(it.carList) }
    }

}