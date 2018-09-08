package io.droidplate.wundertest.model

import android.os.Parcel
import android.os.Parcelable
import io.droidplate.domain.model.Car
import javax.inject.Inject

data class CarItem(val id: Int,
                   val address: String,
                   val engineType: String,
                   val exterior: String,
                   val fuel: Int,
                   val interior: String,
                   val name: String,
                   val vin: String,
                   val lat: Double,
                   val long: Double) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readDouble()


    )

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest) {

        writeInt(id)
        writeString(address)
        writeString(engineType)
        writeString(exterior)
        writeInt(fuel)
        writeString(interior)
        writeString(name)
        writeString(vin)
        writeDouble(lat)
        writeDouble(long)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        var CREATOR: Parcelable.Creator<CarItem> = object : Parcelable.Creator<CarItem> {
            override fun createFromParcel(source: Parcel): CarItem = CarItem(source)
            override fun newArray(size: Int): Array<CarItem?> = arrayOfNulls(size)
        }
    }

}


class CardItemMapper @Inject constructor() {

    fun mapToPresentation(car: Car): CarItem = CarItem(car.id, car.address, car.engineType, car.exterior, car.fuel, car.interior, car.name, car.vin, car.lat, car.long)

    fun mapToPresentation(list: List<Car>): List<CarItem> = list.map { mapToPresentation(it) }
}