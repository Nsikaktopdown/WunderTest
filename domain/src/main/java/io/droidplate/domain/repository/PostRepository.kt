package io.droidplate.domain.repository

import io.droidplate.domain.model.Post
import io.reactivex.Flowable
import io.reactivex.Single

interface PostRepository {
    fun fetchPost(refresh: Boolean): Single<List<Post>>
    fun getAllPost(refresh: Boolean): Flowable<List<Post>>
}