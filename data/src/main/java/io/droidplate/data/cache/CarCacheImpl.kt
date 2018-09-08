package io.droidplate.data.cache

import io.droidplate.data.db.AppDatabase
import io.droidplate.data.model.CarEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CarCacheImpl @Inject constructor(val database: AppDatabase) : CarCache {

    override fun insertCars(cars: List<CarEntity>): Completable {
        return Completable.fromAction {
            kotlin.run {
                database.carDao().saveCars(cars)
                getAllCars()
            }
        }.doOnComplete { Timber.d("Cars saved successfully") }
    }


    override fun getAllCars(): Single<List<CarEntity>> {
        return database.carDao().getAllCars()
    }

}