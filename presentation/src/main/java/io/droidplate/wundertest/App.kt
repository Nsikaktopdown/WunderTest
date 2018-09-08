package io.droidplate.wundertest

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.droidplate.wundertest.di.AppModule
import io.droidplate.wundertest.di.DaggerInjector
import io.droidplate.wundertest.di.Injector
import io.fabric.sdk.android.Fabric

class App : Application() {

    lateinit var injector: Injector private set

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

       initDagger()

    }

    /**
     * Initial Dagger Instance in the application class
     * Making this available anywhere in the app
     */
    private fun initDagger() {
        injector = DaggerInjector
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}