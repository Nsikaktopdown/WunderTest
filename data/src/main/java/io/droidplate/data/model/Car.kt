package io.droidplate.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import io.droidplate.domain.model.Car
import io.reactivex.Single
import java.io.Serializable

data class CarData(@Json(name = "address") val address: String,
                   @Json(name = "engineType") val engineType: String,
                   @Json(name = "exterior") val exterior: String,
                   @Json(name = "fuel") val fuel: Int,
                   @Json(name = "interior") val interior: String,
                   @Json(name = "name") val name: String,
                   @Json(name = "vin") val vin: String,
                   @Json(name = "coordinates") val coordinates: List<Double>)


data class CarsResponse(@Json(name = "placemarks") val carList: List<CarData>)

