package com.urbn.android.flickster

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.urbn.android.flickster.data.ApiClient
import com.urbn.android.flickster.data.remote.CharacterRepositoryImpl
import com.urbn.android.flickster.data.remote.CharacterRoomRepository
import com.urbn.android.flickster.databinding.FragmentCharacterListBinding
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.db.DatabaseService
import com.urbn.android.flickster.presentation.adapters.CharacterAdapter
import com.urbn.android.flickster.presentation.util.SortMethod

import com.urbn.android.flickster.presentation.view_model.CharacterViewModel
import com.urbn.android.flickster.presentation.view_model.CharacterViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    lateinit var characterViewModel: CharacterViewModel
    private var characterEntity : List<CharacterEntity>? =null
    private lateinit var adapter: CharacterAdapter
    private var characterRoomRepository:CharacterRoomRepository? =null
   private var databaseService:DatabaseService? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        //val characterRepoApi = ApiClient.provideApiService(ApiClient.provideRetrofit(OkHttpClient.Builder().build()))
        val characterRepoApi = ApiClient.getCharacterService()
        val characterRepositoryImpl = CharacterRepositoryImpl(characterRepoApi)
         databaseService = context?.let { DatabaseService.getInstance(it) }
        characterRoomRepository = databaseService?.let { CharacterRoomRepository(it.characterDao()) }
        characterViewModel =
            ViewModelProvider(this, CharacterViewModelFactory(characterRepositoryImpl,characterRoomRepository)).get(
                CharacterViewModel::class.java
            )
        setHasOptionsMenu(true)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList
        adapter = CharacterAdapter()
     // characterViewModel.getAllCharacters()

        lifecycleScope.launch {
            val characters = withContext(Dispatchers.IO) {
                databaseService?.characterDao()?.getAllCharacters()
            }
            withContext(Dispatchers.Main) {
                if (characters != null && characters.isNotEmpty()) {
                    adapter.submitList(characters)
                } else {
                    characterViewModel.fetchProducts()
                }
            }
        }

//        characterRoomRepository?.getAllCharacters()?.observe(viewLifecycleOwner){
//            if(it.isNotEmpty()){
//                adapter.submitList(it)
//            }else{
//                characterViewModel.fetchProducts()
//            }
//        }


        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sorting_menu, menu)

        // Set up a listener to handle the menu item clicks
        menu.findItem(R.id.action_sort_alphabetical).setOnMenuItemClickListener {
            // Handle the "Sort Alphabetical" click
           val sortedList = characterViewModel.sortList(SortMethod.ALPHABETICAL,characterEntity)
            adapter.submitList(sortedList)
            true
        }
        menu.findItem(R.id.action_sort_reverse_alphabetical).setOnMenuItemClickListener {
            // Handle the "Sort Reverse Alphabetical" click
            val sortedList = characterViewModel.sortList(SortMethod.REVERSE_ALPHABETICAL,characterEntity)
            adapter.submitList(sortedList)
            true
        }
    }
    }




