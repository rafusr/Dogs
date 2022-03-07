package com.andikas.dogs.data.network

import com.andikas.dogs.data.response.DogsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    fun searchImages(
        @Query("size") size: String? = null,
        @Query("mime_types") mimeType: String? = null,
        @Query("format") format: String? = null,
        @Query("has_breeds") hasBreeds: Boolean? = null,
        @Query("order") order: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("key") key: String? = null
    ): Call<DogsResponse>

}