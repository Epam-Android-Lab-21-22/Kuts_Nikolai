package com.example.ui_app_nikolai_kuts.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.databinding.ItemColorLayoutBinding
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor
import com.example.ui_app_nikolai_kuts.domain.update
import com.example.ui_app_nikolai_kuts.presentation.common.ColorAssistant

class ItemColorAdapter : RecyclerView.Adapter<ItemColorAdapter.ViewHolder>() {

    private val elements: MutableList<ItemColor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColorLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            numberTextView.text = position.toString()

            val colorAssistant = ColorAssistant().apply {
                setBackgroundTint(
                    view = root,
                    color = getColorAsInt(elements[position])
                )
            }
        }
    }

    override fun getItemCount(): Int = elements.size

    fun updateData(itemColors: List<ItemColor>) {
        elements.update(itemColors)
        notifyDataSetChanged()
    }

    fun addElement(newColor: ItemColor) {
        elements.add(newColor)
        notifyItemInserted(elements.size)
    }

    class ViewHolder(val binding: ItemColorLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}