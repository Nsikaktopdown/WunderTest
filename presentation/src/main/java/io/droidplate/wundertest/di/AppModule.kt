package io.droidplate.wundertest.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.droidplate.data.cache.CarCache
import io.droidplate.data.cache.CarCacheImpl
import io.droidplate.wundertest.App
import io.droidplate.data.db.AppDatabase
import javax.inject.Singleton


@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    /**
     * Room Database instance
     * @param app application context
     */
    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "wunder_test.db").build()
    }

    /**
     * AppCache instance
     * @param appCacheImpl
     */
    @Provides
    @Singleton
    fun provideAppCache(database: AppDatabase): CarCache = CarCacheImpl(database)



}