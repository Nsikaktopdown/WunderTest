package io.droidplate.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(@PrimaryKey(autoGenerate = true)
                     var id: Int,
                     var address: String,
                     var engineType: String,
                     var exterior: String,
                     var fuel: Int,
                     var interior: String,
                     var name: String,
                     var vin: String,
                     var lat: Double,
                     var long: Double){
    constructor() : this(0, "", "", "", 0, "", "", "",0.0,0.0)
}
