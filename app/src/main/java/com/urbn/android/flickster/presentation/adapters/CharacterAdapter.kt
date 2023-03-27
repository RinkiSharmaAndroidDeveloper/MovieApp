package com.urbn.android.flickster.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.urbn.android.flickster.R
import com.urbn.android.flickster.data.CharacterDetail
import com.urbn.android.flickster.databinding.CharacterItemContentBinding
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.presentation.fragments.CharacterDetailFragment

class CharacterAdapter : ListAdapter<CharacterEntity, CharacterViewHolder>(CharactersDiffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }


}


class CharacterViewHolder(binding: CharacterItemContentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val textView: TextView = binding.characterName
    private val imageView: ImageView = binding.characterPicture

    fun onBind(character: CharacterEntity) {
        val imageUrl = character.imageUrl
        textView.text = character.name
        Picasso.get().load(imageUrl).into(imageView)
        itemView.setOnClickListener { itemView ->
            val bundle = Bundle()
            bundle.putParcelable(
                CharacterDetailFragment.ARG_CHARACTER,
                CharacterDetail(character.id,character.name,character.details,character.imageUrl,character.isFavorite)
            )

            itemView.findNavController().navigate(R.id.show_character_detail, bundle)
        }
    }
}


object CharactersDiffUtil : DiffUtil.ItemCallback<CharacterEntity>() {
    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }
}