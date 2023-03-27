package com.urbn.android.flickster.data.remote

import android.util.Log
import com.urbn.android.flickster.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl constructor(private val api: CharacterRepoApi) {

//     suspend fun getCharactersList(): Resource<CharacterListData> {
//        return try {
//            withContext(Dispatchers.IO) {
//                // Return a Resource.Loading value to indicate that the request is in progress
//                Resource.Loading(loadingStatus = true, data = null)
//
//                // Actually perform the API request and convert the response to CharacterListDataResult objects
//                val response = api.getCharacterList()
////                if(response.isSuccessful()){
////                    Log.e("hi","yes")
////                }
//              //  val resultList = ArrayList<CharacterListResponse>()
////                response.forEach { pokemonListResponse ->
////                    pokemonListResponse.relatedTopics.forEach { relatedTopic ->
////                        resultList.add(
////                            CharacterListDataResult(
////                                name = relatedTopic.text,
////                                details = "",
////                                imageUrl = relatedTopic.icon.url
////                            )
////                        )
////                    }
////                }
//                Log.d("value_response",response.toString())
//
//        Resource.Success(data = response)
//
//
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            withContext(Dispatchers.IO) {
//                // If an error occurs, return a Resource.Failure value with the error message
//                Resource.Failure(e.localizedMessage ?: "An unknown error occurred")
//            }
//        }
//    }
}