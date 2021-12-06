package com.example.ui_app_nikolai_kuts

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.databinding.CharacterItemBinding


class TextAdapter : RecyclerView.Adapter<TextAdapter.CharacterHolder>() {

    private companion object {
        const val EMPTY_CHARACTER = " "
        const val MIN_ALPHA = 0f
        const val TRANSACTION_VALUE = 200f
        const val TRANSLATION_DESTINATION = 0f
        const val EMPTY_ITEM_ALPHA = 0.7f
        const val MAX_ALPHA = 1F

        val DELAY_RANGE = 0..300
        val DURATION_RANGE = 500..1500
    }

    var characters: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemBinding.inflate(inflater, parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = characters[position]

        holder.binding.characterTextView.apply {
            text = character
            val backgroundColor = ColorAssistant(context = this.context).getRandomColor()
            ViewCompat.setBackgroundTintList(
                this,
                ColorStateList.valueOf(backgroundColor)
            )
            animateItem(
                view = this,
                isEmpty = text == EMPTY_CHARACTER
            )
        }
    }

    override fun getItemCount(): Int = characters.size

    private fun animateItem(view: View, isEmpty: Boolean) {
        view.animate().apply {
            view.alpha = MIN_ALPHA
            view.translationY = -TRANSACTION_VALUE

            startDelay = (DELAY_RANGE).random().toLong()
            duration = (DURATION_RANGE).random().toLong()
            interpolator = BounceInterpolator()
            translationY(TRANSLATION_DESTINATION)
            if (isEmpty) {
                alpha(EMPTY_ITEM_ALPHA)
            } else {
                alpha(MAX_ALPHA)
            }
            start()
        }
    }

class CharacterHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root)
}
