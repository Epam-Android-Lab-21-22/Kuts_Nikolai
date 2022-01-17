package com.example.ui_app_nikolai_kuts.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.databinding.FragmentUserBinding
import com.example.ui_app_nikolai_kuts.presentation.adapters.UserAdapter
import com.example.ui_app_nikolai_kuts.presentation.presenters.UserPresenter
import com.example.ui_app_nikolai_kuts.presentation.view_intefaces.UserView

class UserFragment : Fragment(R.layout.fragment_user), UserView {

    private val binding: FragmentUserBinding by viewBinding(FragmentUserBinding::bind)
    private val firstAdapter = UserAdapter(::deleteUser)
    private val presenter: UserPresenter by lazy {
        UserPresenter(this, requireActivity().getPreferences(Context.MODE_PRIVATE))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.firstRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = firstAdapter
        }

        presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.firstRecyclerView.adapter = null
    }

    override fun onGetData(users: List<User>) {
        firstAdapter.updateData(users)
    }

    private fun deleteUser(user: User) {
        presenter.deleteUser(user)
    }

    override fun onDataUpdated(users: List<User>) {
        firstAdapter.updateData(newUsers = users)
    }
}