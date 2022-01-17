package com.example.ui_app_nikolai_kuts

import androidx.recyclerview.widget.DiffUtil
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User.*

class FirstDiffUtilCallback(
    private val oldList: List<User>,
    private val newList: List<User>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return when {
            areBothInstanceOf<RegularPerson>(old = oldItem, new = newItem) -> oldItem == newItem
            areBothInstanceOf<Worker>(old = oldItem, new = newItem) -> oldItem == newItem
            areBothInstanceOf<Customer>(old = oldItem, new = newItem) -> oldItem == newItem
            else -> false
        }
    }

    private inline fun <reified T: User> areBothInstanceOf(old: User, new: User): Boolean {
        return old is T && new is T
    }
}