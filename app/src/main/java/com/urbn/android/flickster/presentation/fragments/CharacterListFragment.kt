package com.urbn.android.flickster

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.urbn.android.flickster.databinding.FragmentCharacterListBinding
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.presentation.adapters.CharacterAdapter
import com.urbn.android.flickster.presentation.util.SortMethod
import com.urbn.android.flickster.presentation.view_model.CharacterViewModel
import com.urbn.android.flickster.presentation.view_model.CharacterViewModelFactory


class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    lateinit var characterViewModel: CharacterViewModel
    private var characterEntity: List<CharacterEntity>? = null
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        val myApplication = requireActivity().application as MyApplication
        val characterRepoApi = myApplication.characterRepoApi

        characterViewModel =
            ViewModelProvider(this, CharacterViewModelFactory(characterRepoApi)).get(
                CharacterViewModel::class.java
            )
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList
        characterViewModel.getAllCharacter()
        adapter = CharacterAdapter()

        //getting data list
        characterViewModel.liveData.observe(viewLifecycleOwner){
            characterEntity = it
            adapter.submitList(characterEntity)
        }

        recyclerView.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sorting_menu, menu)

        // Set up a listener to handle the menu item clicks
        menu.findItem(R.id.action_sort_alphabetical).setOnMenuItemClickListener {
            // Handle the "Sort Alphabetical" click
            val sortedList = characterViewModel.sortList(SortMethod.ALPHABETICAL, characterEntity)
            adapter.submitList(sortedList)
            true
        }
        menu.findItem(R.id.action_sort_reverse_alphabetical).setOnMenuItemClickListener {
            // Handle the "Sort Reverse Alphabetical" click
            val sortedList =
                characterViewModel.sortList(SortMethod.REVERSE_ALPHABETICAL, characterEntity)
            adapter.submitList(sortedList)
            true
        }
    }

}




