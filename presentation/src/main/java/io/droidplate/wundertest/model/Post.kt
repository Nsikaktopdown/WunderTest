package io.droidplate.wundertest.model

import io.droidplate.domain.model.Post
import java.io.Serializable
import javax.inject.Inject

data class PostItem(val postId: String, val title: String, val body: String) : Serializable

/**
 * Presentation layer should be responsible of mapping the domain model to an appropriate presentation model and the presentation model to a domain model if needed.
 *
 * This is because domain should contain only business logic and shouldn't know at all about presentation or data layers.
 */
class PostItemMapper @Inject constructor() {

    fun mapToPresentation(cup: Post): PostItem = PostItem(cup.id, cup.title, cup.body)

    fun mapToPresentation(postList: List<Post>): List<PostItem> = postList.map { mapToPresentation(it) }

}