package io.droidplate.wundertest.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.droidplate.wundertest.ViewModelFactory
import io.droidplate.wundertest.ViewModelKey
import io.droidplate.wundertest.ui.cars.CarActivityViewModel
import io.droidplate.wundertest.ui.maps.CarMapsViewModel


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CarActivityViewModel::class)
    internal abstract fun cardViewModel(viewModel: CarActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarMapsViewModel::class)
    internal abstract fun cardMapsViewModel(viewModel: CarMapsViewModel): ViewModel
}