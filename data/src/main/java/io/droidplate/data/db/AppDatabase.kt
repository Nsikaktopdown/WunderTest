package io.droidplate.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.droidplate.data.model.CarEntity
import io.droidplate.data.model.PostEntity

@Database(entities = arrayOf(PostEntity::class, CarEntity::class), version = 1)
public abstract class AppDatabase: RoomDatabase() {

   public abstract fun postDao(): PostDao
   public abstract fun carDao(): CarDao
}