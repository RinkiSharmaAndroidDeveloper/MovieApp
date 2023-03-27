package com.urbn.android.flickster.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.urbn.android.flickster.data.CharacterDetail
import com.urbn.android.flickster.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private var character :CharacterDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        character = arguments?.getParcelable<CharacterDetail>("character")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textName.title = character?.name
        binding.detailTextView.text = character?.details
        Picasso.get().load(character?.imageUrl).into(binding.imageView)
    }

    companion object {
        const val ARG_CHARACTER = "character"
    }
}
