package io.droidplate.wundertest.di

import dagger.Binds
import dagger.Module
import io.droidplate.data.repository.CarRepositoryImpl
import io.droidplate.domain.repository.CarRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(repository: CarRepositoryImpl): CarRepository

}