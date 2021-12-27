package com.example.ui_app_nikolai_kuts.second_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.ColorAssistant
import com.example.ui_app_nikolai_kuts.databinding.ItemGrindLayoutBinding

class SecondAdapter : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    private val elements = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGrindLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            numberTextView.text = position.toString()
            ColorAssistant().setBackgroundTint(view = root, color = elements[position])
        }
    }

    override fun getItemCount(): Int = elements.size

    fun addElement(newColor: Int) {
        elements.add(newColor)
        notifyItemInserted(elements.size)
    }

    class ViewHolder(val binding: ItemGrindLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}