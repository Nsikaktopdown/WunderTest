package io.droidplate.wundertest.ui.cars

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.droidplate.domain.usecase.CarsUserCase
import io.droidplate.domain.usecase.UserPostUseCase
import io.droidplate.wundertest.Data
import io.droidplate.wundertest.DataState
import io.droidplate.wundertest.model.CarItem
import io.droidplate.wundertest.model.CardItemMapper
import io.droidplate.wundertest.model.PostItem
import io.droidplate.wundertest.model.PostItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarActivityViewModel @Inject constructor(private val useCase: CarsUserCase,
                                        private val mapper:  CardItemMapper) : ViewModel(){

    val cars = MutableLiveData<Data<List<CarItem>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getCars(false)
    }

    fun getCars(refresh: Boolean = false) =
            compositeDisposable.add(useCase.getCars(refresh)
                    .doOnSubscribe { cars.postValue(Data(dataState = DataState.LOADING, data = cars.value?.data, message = null)) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe({
                        cars.postValue(Data(dataState = DataState.SUCCESS, data = it, message = null))
                    }, { cars.postValue(Data(dataState = DataState.ERROR, data = cars.value?.data, message = it.message)) }))



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}