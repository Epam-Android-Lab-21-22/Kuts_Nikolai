package com.example.ui_app_nikolai_kuts.FirstUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.FirstDiffUtilCallback
import com.example.ui_app_nikolai_kuts.FirstUI.FirstViewHolder.FirstTypeItemHolder
import com.example.ui_app_nikolai_kuts.ItemTypes.*
import com.example.ui_app_nikolai_kuts.User
import com.example.ui_app_nikolai_kuts.databinding.ItemCustomerBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemRegularPersonBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemWorkerBinding
import com.example.ui_app_nikolai_kuts.update
import java.lang.Exception

class FirstAdapter : RecyclerView.Adapter<FirstViewHolder>() {

    private val users = mutableListOf<User>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            FIRST_ITEM_TYPE.ordinal -> {
                FirstTypeItemHolder(
                    ItemRegularPersonBinding.inflate(inflater, parent, false)
                )
            }
            SECOND_ITEM_TYPE.ordinal -> {
                FirstViewHolder.SecondTypeItemHolder(
                    ItemWorkerBinding.inflate(inflater, parent, false)
                )
            }
            THIRD_ITEM_TYPE.ordinal -> {
                val thirdTypeItemHolder = FirstViewHolder.ThirdTypeItemHolder(
                    ItemCustomerBinding.inflate(inflater, parent, false)
                )
                thirdTypeItemHolder
            }
            else -> throw Exception("unknown item type")
        }
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        holder.apply {
            onBind(users[position])

            if (this is FirstTypeItemHolder) {
                binding.regularPersonCloseIcon.setOnClickListener {

                    if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                        val newUsers: List<User> = ArrayList(users).apply {
                            remove(users[absoluteAdapterPosition])
                        }
                        updateUsers(newUsers)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = users.size

    override fun getItemViewType(position: Int): Int {
        return when (users[position]) {
            is User.RegularPerson -> FIRST_ITEM_TYPE.ordinal
            is User.Worker -> SECOND_ITEM_TYPE.ordinal
            is User.Customer -> THIRD_ITEM_TYPE.ordinal
        }
    }

    fun updateData(newUsers: List<User>) {
        updateUsers(newUsers)
    }

    private fun updateUsers(newUsers: List<User>) {
        val diffResult = DiffUtil.calculateDiff(FirstDiffUtilCallback(users, newUsers))
        users.update(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }
}