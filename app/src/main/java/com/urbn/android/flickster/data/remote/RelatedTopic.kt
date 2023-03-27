package com.urbn.android.flickster.data.remote

data class RelatedTopic(
    val FirstURL: String,
    val Icon: Icon,
    val Result: String,
    val Text: String
)
fun getFormattedName(name: String?): String?{
    val textParts = name?.split(" - ")
    return textParts?.getOrElse(0) { "" }
}
fun getDetails(name: String?): String?{
    val textParts = name?.split(" - ")
    return textParts?.getOrElse(1) { "" }
}
fun getImageUrl(url:String?):String?{
    return "https://www.duckduckgo.com/${url}"
}
//{
//    val name: String
//    val details: String
//    val imageUrl: String
//
//    init {
//        val textParts = Text.split(" - ")
//        name = textParts.getOrElse(0) { "" }
//        details = textParts.getOrElse(1) { "" }
//        imageUrl = "https://www.duckduckgo.com/i/${Icon.URL}"
//    }
//}
