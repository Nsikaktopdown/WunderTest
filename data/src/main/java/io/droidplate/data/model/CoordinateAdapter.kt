package io.droidplate.data.model

import android.os.Parcel
import android.os.Parcelable
import android.util.JsonToken
import com.google.android.gms.maps.model.LatLng
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


class CoordinateJsonAdapter: TypeAdapter<Coordinate>() {

    override fun write(writer: JsonWriter, coordinate: Coordinate) {

        val (lat, lng) = coordinate
        writer.value("$lat, $lng")
    }

    override fun read(reader: JsonReader): Coordinate? {

        val peek = reader.peek()

        if (peek == JsonToken.STRING) {
            val value = reader.nextString()
            val parts = value.split(',')

            if (parts.size < 2) return null

            return Coordinate(parts[0].toDouble(), parts[1].toDouble())
        }

        else if (peek == JsonToken.NULL) reader.nextNull()

        return null
    }
}


@JsonAdapter(CoordinateJsonAdapter::class)
data class Coordinate(
        val lat: Double,
        val lng: Double
) : Parcelable {
    fun toLatLng() = LatLng(lat, lng)

    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(lat)
        writeDouble(lng)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Coordinate> = object : Parcelable.Creator<Coordinate> {
            override fun createFromParcel(source: Parcel): Coordinate = Coordinate(source)
            override fun newArray(size: Int): Array<Coordinate?> = arrayOfNulls(size)
        }
    }
}

