package com.maksimzotov.notebook.data.remote.about

import com.maksimzotov.notebook.data.remote.about.deserializers.ItemsAboutWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface AboutApi {

    @GET("top-headlines")
    suspend fun getItemsAbout(
        @Query("country") country: String = Locale.getDefault().language,
        @Query("apiKey") key: String = "f3d22a6242cc4b68aad52cf201088dc6"
    ): Response<ItemsAboutWrapper>
}