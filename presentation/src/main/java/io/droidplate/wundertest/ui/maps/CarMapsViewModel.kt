package io.droidplate.wundertest.ui.maps

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.droidplate.domain.usecase.CarMapsUseCase
import io.droidplate.domain.usecase.CarsUserCase
import io.droidplate.wundertest.Data
import io.droidplate.wundertest.DataState
import io.droidplate.wundertest.model.CarItem
import io.droidplate.wundertest.model.CardItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarMapsViewModel @Inject constructor(private val useCase: CarMapsUseCase,
                                           private val mapper: CardItemMapper) : ViewModel(){

    val cars = MutableLiveData<Data<List<CarItem>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getCars()
    }

    fun getCars() =
            compositeDisposable.add(useCase.getCars()
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
