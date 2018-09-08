package io.droidplate.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.droidplate.data.model.CarEntity
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAllCars(): Single<List<CarEntity>>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun saveCars(cars: List<CarEntity>)
}