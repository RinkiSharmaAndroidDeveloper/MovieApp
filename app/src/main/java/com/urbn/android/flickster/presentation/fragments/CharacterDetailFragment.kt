package com.urbn.android.flickster.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.urbn.android.flickster.MyApplication
import com.urbn.android.flickster.R
import com.urbn.android.flickster.data.CharacterDetail
import com.urbn.android.flickster.databinding.FragmentCharacterDetailBinding
import com.urbn.android.flickster.presentation.view_model.CharacterViewModel
import com.urbn.android.flickster.presentation.view_model.CharacterViewModelFactory

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private var character :CharacterDetail? = null
    lateinit var characterViewModel: CharacterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val myApplication = requireActivity().application as MyApplication
        val characterRepoApi = myApplication.characterRepoApi

        characterViewModel =
            ViewModelProvider(this, CharacterViewModelFactory(characterRepoApi)).get(
                CharacterViewModel::class.java
            )
        character = arguments?.getParcelable(ARG_CHARACTER)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = character?.name
        binding.detailTextView.text = character?.details
        Picasso.get().load(character?.imageUrl).into(binding.imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bookmar_menu, menu)
        val bookmarkMenuItem= menu.findItem(R.id.bookmark_menu_item)
        if(character?.isFavorite ==true){
            bookmarkMenuItem.setIcon(R.drawable.ic_star_accent)
        }
        // Set up a listener to handle the menu item clicks
        bookmarkMenuItem.setOnMenuItemClickListener {
            if(character?.isFavorite ==true){
                //Removing bookmarking the movie
                character?.id?.let { id ->
                    characterViewModel.updateIsFavorite(id,
                        false
                    )
                }
                // Change the menu icon at runtime
                bookmarkMenuItem.setIcon(R.drawable.ic_star_hollow)
            }else{
                //bookmarking the movie
                character?.id?.let { id ->
                    characterViewModel.updateIsFavorite(id,
                        true
                    )
                }
                // Change the menu icon at runtime
                bookmarkMenuItem.setIcon(R.drawable.ic_star_accent)
            }

            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
    companion object {
        const val ARG_CHARACTER = "character"
    }
}
