package io.droidplate.data.repository

import io.droidplate.data.mapper.PostMapper
import io.droidplate.data.remote.RemoteApi
import io.droidplate.domain.model.Post
import io.droidplate.domain.repository.AppRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Here implements all the api interface
 * Binding this it to the domain
 * @param api retrofitr inteface
 * @param mapper data passer
 */
class AppRemoteImpl @Inject constructor(private val api: RemoteApi, private val mapper: PostMapper) {


}