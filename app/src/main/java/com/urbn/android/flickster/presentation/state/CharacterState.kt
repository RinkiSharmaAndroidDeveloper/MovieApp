package com.urbn.android.flickster.presentation.state

import com.urbn.android.flickster.data.remote.CharacterListData
import com.urbn.android.flickster.data.remote.RelatedTopic



data class CharacterState(
// val characterList: List<RelatedTopic>? =null,
   val characterList:CharacterListData?,
    val isLoading: Boolean = false,
    val error: String? = null,
   // val sortMethod: SortMethod = SortMethod.NONE
)



