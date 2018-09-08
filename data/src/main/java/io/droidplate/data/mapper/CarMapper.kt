package io.droidplate.data.mapper

import io.droidplate.data.model.CarData
import io.droidplate.data.model.CarEntity
import io.droidplate.data.model.CarsResponse
import io.droidplate.data.model.Coordinate
import io.droidplate.domain.model.Car
import javax.inject.Inject

class CarMapper @Inject constructor() {

    fun mapToDomain(car: CarData): Car = Car(0, car.address, car.engineType, car.exterior, car.fuel, car.interior, car.name, car.vin, car.coordinates[0], car.coordinates[0])

    fun mapToDomain(list: List<CarData>): List<Car> = list.map { mapToDomain(it) }


    fun passLong(coordinate: Coordinate): Double = coordinate.let { it.lng }

    fun passLat(coordinate: List<Coordinate>): Double {

        var lat = 0.0
        for (i in coordinate.indices) {
            lat = coordinate[i].lat
        }
        return lat
    }


    fun mapToEntity(car: Car): CarEntity = CarEntity(0, car.address, car.engineType, car.exterior, car.fuel, car.interior, car.name, car.vin, car.lat, car.long)

    fun mapToEntity(cars: List<Car>): List<CarEntity> = cars.map { mapToEntity(it) }

    fun mapEntityToDomain(entity: CarEntity): Car = Car(entity.id, entity.address, entity.engineType, entity.exterior, entity.fuel, entity.interior, entity.name, entity.vin, entity.lat, entity.long)

    fun mapEntityToDomain(cars: List<CarEntity>): List<Car> = cars.map { mapEntityToDomain(it) }
}