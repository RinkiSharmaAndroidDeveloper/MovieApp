package com.urbn.android.flickster.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbn.android.flickster.data.ApiClient
import com.urbn.android.flickster.data.remote.*
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.presentation.state.CharacterState
import com.urbn.android.flickster.presentation.util.SortMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(
    private val characterRepoApi: CharacterRepositoryImpl,
    private val characterRoomRepository: CharacterRoomRepository?
) : ViewModel() {

    private var mutableLiveData: MutableLiveData<CharacterState> = MutableLiveData()
    val pro = ApiClient.getCharacterService()
    val liveData: MutableLiveData<CharacterState> get() = mutableLiveData

    private var _characters = MutableLiveData<List<CharacterEntity>>()
    val characters: LiveData<List<CharacterEntity>>
        get() = _characters

    //private var list = mutableListOf<CharacterEntity>()

//    fun getCharacterListData() {
//        viewModelScope.launch {
//            when (val result = characterRepoApi.getCharactersList()) {
//                is Resource.Loading -> {
//                    mutableLiveData.value = result.data?.let {
//                        CharacterState(
//                            isLoading = result.loadingStatus,
//                            error = result.message
//                        )
//                    }
//                }
//                is Resource.Success-> {
//                    mutableLiveData.value = result.data?.let {
//                        CharacterState(
//                            characterList = it.RelatedTopics,
//                            isLoading = result.loadingStatus,
//                            error = result.message
//                        )
//                    }
//                }
//                is Resource.Failure -> {
//                    mutableLiveData.value = result.data?.let {
//                        CharacterState(
//                            isLoading = result.loadingStatus,
//                            error = result.message
//                        )
//                    }
//                    Log.e("Error", result.message ?: "Unknown error")
//                }
//            }
//        }
//    }

    fun sortList(
        sortMethod: SortMethod = SortMethod.NONE,
        characterEntity : List<CharacterEntity>?
    ): List<CharacterEntity>? {
        return if (sortMethod == SortMethod.ALPHABETICAL) characterEntity?.sortedBy { it.name }
        else characterEntity?.sortedByDescending { it.name }
    }

    fun fetchProducts() {
        viewModelScope.launch {
            val response = pro.getCharacterList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
//                    mutableLiveData.value = CharacterState(
//                        characterList = response.body(),
//                        isLoading = false,
//                        error = "",
//                        sortMethod = SortMethod.NONE
//                    )

                    val characterEntities = response.body()?.RelatedTopics?.map { it ->
                        CharacterEntity(
                            name = getFormattedName(it.Text) ?: "",
                            details = getDetails(it.Text) ?: "",
                            imageUrl = getImageUrl(it.Icon.URL),
                            isFavorite = false
                        )
                    }

                    characterEntities.let {
                        if (it != null) {
                            characterRoomRepository?.insertAll(it)
                        }
                    }
                } else {
                    // onError("Error: ${response.message()}")
                }
            }
        }
    }


    fun getAllCharacters() {

//        viewModelScope.launch {
//
//            characterRoomRepository?.getAllCharacters()
//            }
    }

//    fun updateIsFavorite(id: Long, isFavorite: Boolean) {
//        viewModelScope.launch {
//            characterRoomRepository?.updateIsFavorite(id, isFavorite)
//        }
//    }
}