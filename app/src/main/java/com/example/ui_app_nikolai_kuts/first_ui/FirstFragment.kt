package com.example.ui_app_nikolai_kuts.first_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.User
import com.example.ui_app_nikolai_kuts.UserGenerator
import com.example.ui_app_nikolai_kuts.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    private val firstAdapter = FirstAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = firstAdapter
        }

        val users: List<User> = UserGenerator().generateUsers(userQuantity = 30)
        firstAdapter.updateData(users)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.firstRecyclerView.adapter = null
    }
}