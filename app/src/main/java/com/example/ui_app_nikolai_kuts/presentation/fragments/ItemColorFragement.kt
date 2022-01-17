package com.example.ui_app_nikolai_kuts.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentItemColorBinding
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor
import com.example.ui_app_nikolai_kuts.presentation.adapters.ItemColorAdapter
import com.example.ui_app_nikolai_kuts.presentation.presenters.ItemColorPresenter
import com.example.ui_app_nikolai_kuts.presentation.view_intefaces.ItemColorView

private const val SPAN_COUNT = 3

class SecondFragment : Fragment(R.layout.fragment_item_color), ItemColorView {

    private val binding: FragmentItemColorBinding by viewBinding(FragmentItemColorBinding::bind)
    private val itemColorAdapter = ItemColorAdapter()
    private val presenter: ItemColorPresenter by lazy {
        ItemColorPresenter(
            view = this,
            preferences =  requireActivity().getPreferences(Context.MODE_PRIVATE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secondRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = itemColorAdapter
        }

        presenter.loadData()

        binding.button.setOnClickListener { presenter.addItemColor() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.secondRecyclerView.adapter = null
    }

    override fun onGetData(itemColors: List<ItemColor>) {
        itemColorAdapter.updateData(itemColors)
    }

    override fun onDataUpdated(itemColors: List<ItemColor>) {
        itemColorAdapter.updateData(itemColors)
        binding.secondRecyclerView.smoothScrollToPosition(itemColorAdapter.itemCount)
    }
}