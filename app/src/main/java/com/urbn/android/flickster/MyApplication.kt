package com.urbn.android.flickster

import android.app.Application
import com.urbn.android.flickster.data.ApiClient
import com.urbn.android.flickster.data.remote.CharacterRepositoryImpl
import com.urbn.android.flickster.db.DatabaseService


class MyApplication :Application() {
    lateinit var characterRepoApi : CharacterRepositoryImpl
    lateinit var databaseService: DatabaseService
    override fun onCreate() {
        super.onCreate()
        databaseService=DatabaseService.getInstance(applicationContext)
        characterRepoApi = CharacterRepositoryImpl(ApiClient.getCharacterService(),  databaseService = databaseService,applicationContext)
    }
}