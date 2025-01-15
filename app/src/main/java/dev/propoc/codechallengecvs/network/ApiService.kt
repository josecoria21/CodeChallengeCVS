package dev.propoc.codechallengecvs.network

import dev.propoc.codechallengecvs.model.ImageResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos_public.gne")
    suspend fun fetchData(
        @Query("format") formato: String = "json",
        @Query("nojsoncallback") valor: Int = 1,
        @Query("tags") name: String
    ): ImageResponseModel
}
