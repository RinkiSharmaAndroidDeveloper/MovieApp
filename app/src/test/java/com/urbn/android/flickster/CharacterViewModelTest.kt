package com.urbn.android.flickster

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.urbn.android.flickster.data.remote.CharacterRepositoryImpl
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.presentation.util.SortMethod
import com.urbn.android.flickster.presentation.view_model.CharacterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.shadows.ShadowLog

@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {
//
//    @Mock
//    lateinit var characterRepoApi: CharacterRepositoryImpl
//
//
//    lateinit var characterViewModel: CharacterViewModel
//
//    @Before
//    fun setup() {
//        ShadowLog.stream = System.out
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val app = ApplicationProvider.getApplicationContext() as MyApplication
//        app.onCreate()
//        characterRepoApi = app.characterRepoApi
//        MockitoAnnotations.openMocks(this)
//        characterViewModel = CharacterViewModel(characterRepoApi)
//    }
//
//    @Test
//    fun testSortList_alphabetical() {
//        val characters = listOf(
//            CharacterEntity(1, "Mike", "", "", false),
//            CharacterEntity(2, "Adam", "", "", false),
//            CharacterEntity(3, "John", "", "", false)
//        )
//        val sortedList = characterViewModel.sortList(SortMethod.ALPHABETICAL, characters)
//        assertEquals(
//            sortedList, listOf(
//                CharacterEntity(2, "Adam", "", "", false),
//                CharacterEntity(3, "John", "", "", false),
//                CharacterEntity(1, "Mike", "", "", false)
//            )
//        )
//    }
//
//    @Test
//    fun testSortList_descending() {
//        val characters = listOf(
//            CharacterEntity(1, "Mike", "", "", false),
//            CharacterEntity(2, "Adam", "", "", false),
//            CharacterEntity(3, "John", "", "", false)
//        )
//        val sortedList = characterViewModel.sortList(SortMethod.REVERSE_ALPHABETICAL, characters)
//        assertEquals(
//            sortedList, listOf(
//                CharacterEntity(1, "Mike", "", "", false),
//                CharacterEntity(3, "John", "", "", false),
//                CharacterEntity(2, "Adam", "", "", false)
//            )
//        )
//    }
//
//    @Test
//    fun testGetAllCharacter() {
//        runBlocking {
//            val mockCharacterList = listOf(
//                CharacterEntity(1, "Mike", "", "", false),
//                CharacterEntity(2, "Adam", "", "", false),
//                CharacterEntity(3, "John", "", "", false)
//            )
//            Mockito.`when`(characterRepoApi.fetchMoviesList()).thenReturn(Unit)
//            characterViewModel.getAllCharacter()
//            delay(1000) // wait for the fetch to complete
//            assertEquals(characterViewModel.liveData.value, mockCharacterList)
//        }
//    }
//
//    @Test
//    fun testUpdateIsFavorite() {
//        runBlocking {
//            val characterId = 1L
//            val isFavorite = true
//            Mockito.`when`(characterRepoApi.updateIsFavorite(characterId, isFavorite))
//                .thenReturn(Unit)
//            characterViewModel.updateIsFavorite(characterId, isFavorite)
//            delay(1000) // wait for the update to complete
//            Mockito.verify(characterRepoApi).updateIsFavorite(characterId, isFavorite)
//        }
//    }
}