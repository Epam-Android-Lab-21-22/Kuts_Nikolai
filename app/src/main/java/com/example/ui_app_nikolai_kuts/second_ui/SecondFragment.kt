package com.example.ui_app_nikolai_kuts.second_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.ColorAssistant
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentSecondBinding

private const val SPAN_COUNT = 3

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val secondAdapter = SecondAdapter()

        binding.secondRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = secondAdapter
        }

        val colorAssistant = ColorAssistant()
        secondAdapter.addElement(newColor = colorAssistant.getRandomColor())

        binding.button.setOnClickListener {
            secondAdapter.addElement(newColor = colorAssistant.getRandomColor())
            binding.secondRecyclerView.smoothScrollToPosition(secondAdapter.itemCount)
        }
    }
}