package io.droidplate.wundertest.di

import dagger.Component
import io.droidplate.wundertest.ui.cars.CarActivity
import io.droidplate.wundertest.ui.maps.CarMapsActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface Injector {

    fun inject(activity: CarActivity)
    fun inject(activity: CarMapsActivity)
}