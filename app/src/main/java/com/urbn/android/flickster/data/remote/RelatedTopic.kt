package com.urbn.android.flickster.data.remote

import com.google.gson.annotations.SerializedName

data class RelatedTopic(
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Text")
    val text: String
)

data class Icon(
    @SerializedName("URL")
    val url: String
)

//Getting name from the data
fun getFormattedName(name: String?): String?{
    val textParts = name?.split(" - ")
    return textParts?.getOrElse(0) { "" }
}
//Sepearting detail from the data
fun getDetails(name: String?): String?{
    val textParts = name?.split(" - ")
    return textParts?.getOrElse(1) { "" }
}
// Creating image url
fun getImageUrl(url:String?):String?{
    return "https://www.duckduckgo.com/${url}"
}
