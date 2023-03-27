package com.urbn.android.flickster.data

import com.urbn.android.flickster.data.remote.CharacterRepoApi
import com.urbn.android.flickster.data.remote.CharacterRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.duckduckgo.com/"


    fun getCharacterService(): CharacterRepoApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CharacterRepoApi::class.java)
    }


}