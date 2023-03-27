package com.urbn.android.flickster.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.urbn.android.flickster.data.remote.CharacterRepositoryImpl
import com.urbn.android.flickster.data.remote.CharacterRoomRepository

class CharacterViewModelFactory(private val characterRepoApi: CharacterRepositoryImpl,private val characterRoomRepository: CharacterRoomRepository?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(characterRepoApi,characterRoomRepository) as T
    }
}