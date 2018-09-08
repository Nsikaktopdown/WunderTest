package io.droidplate.domain.repository

import io.droidplate.domain.model.Post
import io.reactivex.Single

interface AppRemote {

    fun fetchPost(): Single<List<Post>>
}