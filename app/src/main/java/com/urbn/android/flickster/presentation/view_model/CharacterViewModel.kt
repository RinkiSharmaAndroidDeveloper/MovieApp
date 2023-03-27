package com.urbn.android.flickster.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbn.android.flickster.data.remote.*
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.presentation.util.SortMethod
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepoApi: CharacterRepositoryImpl,
) : ViewModel() {

    // Get characters LiveData from CharacterRepositoryImpl class
    val liveData: LiveData<List<CharacterEntity>> get() = characterRepoApi.characters

    fun sortList(
        sortMethod: SortMethod = SortMethod.NONE,
        characterEntity: List<CharacterEntity>?
    ): List<CharacterEntity>? {
        val list = if(sortMethod == SortMethod.ALPHABETICAL) {characterEntity?.sortedBy { it.name }}
        else {characterEntity?.sortedByDescending { it.name }}
        return list
    }

    // Call fetchMoviesList() function from CharacterRepositoryImpl class
    fun getAllCharacter() {
        viewModelScope.launch { characterRepoApi.fetchMoviesList() }
    }


    fun updateIsFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            characterRepoApi?.updateIsFavorite(id, isFavorite)
        }
    }
}