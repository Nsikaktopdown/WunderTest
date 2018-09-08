package io.droidplate.data.mapper

import io.droidplate.data.model.PostEntity
import io.droidplate.domain.model.Post
import javax.inject.Inject

/**
 * Entity mapper for data-flow between layers
 */
class PostMapper @Inject constructor() {

    fun mapToDomain(entity: PostEntity): Post = Post(entity.userId,
            entity.id,
            entity.title,
            entity.body)

    fun mapToDomain(list: List<PostEntity>): List<Post> = list.map { mapToDomain(it) }

    fun mapToEntity(post: Post): PostEntity = PostEntity(post.userId,
            post.id,
            post.title,
            post.body)

    fun mapToEntity(list: List<Post>): List<PostEntity> = list.map { mapToEntity(it) }
}