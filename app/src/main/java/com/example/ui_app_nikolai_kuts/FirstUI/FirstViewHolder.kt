package com.example.ui_app_nikolai_kuts.FirstUI

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ui_app_nikolai_kuts.User
import com.example.ui_app_nikolai_kuts.databinding.ItemCustomerBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemRegularPersonBinding
import com.example.ui_app_nikolai_kuts.databinding.ItemWorkerBinding
import java.lang.Exception

sealed class FirstViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(user: User)

    class FirstTypeItemHolder(
        val binding: ItemRegularPersonBinding,
    ) : FirstViewHolder(binding) {

        override fun onBind(user: User) {
            if (user is User.RegularPerson) {
                binding.regularPersonName.text = user.name
                binding.regularPersonCounty.text = user.country
                binding.regularPersonCity.text = user.city
            } else {
                throw Exception("Unsuitable passed type")
            }
        }
    }

    class SecondTypeItemHolder(
        private val binding: ItemWorkerBinding,
    ) : FirstViewHolder(binding) {
        override fun onBind(user: User) {
            if (user is User.Worker) {
                binding.workerFirstName.text = user.firstName
                binding.workerLastName.text = user.lastname
                binding.workerCompany.text = user.company
            } else {
                throw Exception("Unsuitable passed type")
            }
        }
    }

    class ThirdTypeItemHolder(
        private val binding: ItemCustomerBinding,
    ) : FirstViewHolder(binding) {
        override fun onBind(user: User) {
            if (user is User.Customer) {
                binding.customerId.text = user.id.toString()
                binding.customerName.text = user.name
                binding.customerAccount.text = user.phoneNumber
            } else {
                throw Exception("Unsuitable passed type")
            }
        }
    }
}