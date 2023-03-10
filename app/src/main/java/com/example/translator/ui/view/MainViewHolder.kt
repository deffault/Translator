package com.example.translator.ui.view

import androidx.recyclerview.widget.RecyclerView
import com.example.translator.databinding.ItemWordBinding
import com.example.translator.domain.entity.Word

class MainViewHolder(
    private val binding: ItemWordBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word) {
        with(binding) {
            tvWord.text = word.text
            tvTranslation.text = word.meanings[0].translation.translationText
        }
    }
}