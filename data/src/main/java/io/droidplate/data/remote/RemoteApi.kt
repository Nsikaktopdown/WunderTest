package io.droidplate.data.remote

import io.droidplate.data.model.CarsResponse
import io.droidplate.data.model.PostEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * All Api endpoint for this projects should be places here
 */

interface RemoteApi {

    @GET("wunderbucket/locations.json")
    @Headers("Accept: application/json",
            "Content-type:application/json")
    fun getCars(): Single<CarsResponse>
}