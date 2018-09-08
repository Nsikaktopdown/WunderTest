package io.droidplate.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.droidplate.data.model.PostEntity
import io.reactivex.Flowable

@Dao
interface PostDao {

    /**
     * Get all saved post using this dao interface method
     */
    @Query("SELECT * FROM post")
    fun getAllPost(): Flowable<List<PostEntity>>


    /**
     * Save post into db
     */
    @Insert(onConflict = REPLACE)
    fun savePost(postEntity: List<PostEntity>)
}