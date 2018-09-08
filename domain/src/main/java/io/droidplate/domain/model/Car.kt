package io.droidplate.domain.model

data class Car(val id: Int,
               val address: String,
               val engineType: String,
               val exterior: String,
               val fuel: Int,
               val interior: String,
               val name: String,
               val vin: String,
               val lat: Double,
               val long: Double)