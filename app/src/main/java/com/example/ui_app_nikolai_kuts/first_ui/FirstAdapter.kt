package com.example.ui_app_nikolai_kuts.first_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.FirstDiffUtilCallback
import com.example.ui_app_nikolai_kuts.ItemTypes.*
import com.example.ui_app_nikolai_kuts.UNKNOWN_ITEM_TYPE
import com.example.ui_app_nikolai_kuts.User
import com.example.ui_app_nikolai_kuts.databinding.ItemCustomerBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemRegularPersonBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemWorkerBinding
import com.example.ui_app_nikolai_kuts.first_ui.FirstViewHolder.*
import com.example.ui_app_nikolai_kuts.update
import java.lang.Exception

class FirstAdapter : RecyclerView.Adapter<FirstViewHolder<out User>>() {

    private val users = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder<out User> {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            FIRST_ITEM_TYPE.ordinal -> {
                FirstTypeItemHolder(
                    ItemRegularPersonBinding.inflate(inflater, parent, false)
                )
            }
            SECOND_ITEM_TYPE.ordinal -> {
                SecondTypeItemHolder(
                    ItemWorkerBinding.inflate(inflater, parent, false)
                )
            }
            THIRD_ITEM_TYPE.ordinal -> {
                val thirdTypeItemHolder = ThirdTypeItemHolder(
                    ItemCustomerBinding.inflate(inflater, parent, false)
                )
                thirdTypeItemHolder
            }
            else -> throw Exception(UNKNOWN_ITEM_TYPE)
        }
    }

    override fun onBindViewHolder(holder: FirstViewHolder<out User>, position: Int) {
        val user = users[position]
        when (holder) {
            is FirstTypeItemHolder ->  {
                holder.apply {
                    bind(user as User.RegularPerson)

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
            is SecondTypeItemHolder -> holder.bind(user as User.Worker)
            is ThirdTypeItemHolder -> holder.bind(user as User.Customer)
        }
    }

    override fun getItemCount(): Int = users.size

    override fun getItemViewType(position: Int): Int {
        return users[position].getListItemType().ordinal
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