package io.droidplate.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "post")
data class PostEntity(@Json(name = "UserId") val userId: String,
                      @PrimaryKey
                      @Json(name = "Id") val id: String,
                      @Json(name = "Title") val title: String,
                      @Json(name = "Body") val body: String)



