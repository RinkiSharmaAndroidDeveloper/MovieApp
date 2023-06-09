package com.urbn.android.flickster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetail(
    val id :Long,
    val name: String,
    val details: String,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
): Parcelable
