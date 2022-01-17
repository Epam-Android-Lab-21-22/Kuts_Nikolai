package com.example.ui_app_nikolai_kuts.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.databinding.ItemCustomerBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemRegularPersonBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemWorkerBinding

sealed class FirstViewHolder<T : User>(
    binding: ViewBinding,
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(user: T)

    class FirstTypeItemHolder(
        val binding: ItemRegularPersonBinding,
    ) : FirstViewHolder<User.RegularPerson>(binding) {

        override fun bind(user: User.RegularPerson) {
            binding.regularPersonName.text = user.name
            binding.regularPersonCounty.text = user.country
            binding.regularPersonCity.text = user.city
        }
    }

    class SecondTypeItemHolder(
        private val binding: ItemWorkerBinding,
    ) : FirstViewHolder<User.Worker>(binding) {

        override fun bind(user: User.Worker) {
            binding.workerFirstName.text = user.firstName
            binding.workerLastName.text = user.lastname
            binding.workerCompany.text = user.company
        }
    }

    class ThirdTypeItemHolder(
        private val binding: ItemCustomerBinding,
    ) : FirstViewHolder<User.Customer>(binding) {

        override fun bind(user: User.Customer) {
            binding.customerId.text = user.id.toString()
            binding.customerName.text = user.name
            binding.customerAccount.text = user.phoneNumber
        }
    }
}