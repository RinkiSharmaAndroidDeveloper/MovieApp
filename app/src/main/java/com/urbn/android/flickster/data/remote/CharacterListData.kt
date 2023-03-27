package com.urbn.android.flickster.data.remote

import com.google.gson.annotations.SerializedName

data class CharacterListData(
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
)