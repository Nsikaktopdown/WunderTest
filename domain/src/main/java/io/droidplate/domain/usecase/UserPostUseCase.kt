package io.droidplate.domain.usecase

import io.droidplate.domain.model.Post
import io.droidplate.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class UserPostUseCase @Inject constructor(private val postRepository: PostRepository) {

    /**
     * This use case to gets the user's post from the Repository in the domain
     */
    fun getPost(refresh: Boolean): Single<List<Post>> =  postRepository.fetchPost(refresh)

}