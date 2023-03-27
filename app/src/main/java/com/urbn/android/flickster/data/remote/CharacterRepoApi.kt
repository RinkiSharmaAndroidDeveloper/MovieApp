package com.urbn.android.flickster.data.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterRepoApi {

    @GET("/")
    suspend fun getCharacterList(
        @Query("q") q: String ="the wire characters",
        @Query("format") format: String="json"
    ): Response<CharacterListData>
}