package com.example.ui_app_nikolai_kuts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.databinding.FragmentRedBinding

class RedFragment : ClickableNumberedFragment(R.layout.fragment_red) {

    private val binding: FragmentRedBinding by viewBinding(FragmentRedBinding::bind)
    override var onFragmentClickListener: OnFragmentClickListener? = null


    companion object {
        fun getInstance(serialNumber: Int = 0): RedFragment {
            return RedFragment().apply {
                arguments = bundleOf(SERIAL_NUMBER_KEY to serialNumber)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentClickListener) {
            onFragmentClickListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.redCounterTextView.text = serialNumber.toString()

        binding.root.setOnClickListener {
            val nextSerialNumber = serialNumber + 1

            onFragmentClickListener?.onClick(nextSerialNumber)

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(RED_BACK_STACK_NAME)
                replace(
                    R.id.fragment_container,
                    getInstance(serialNumber = nextSerialNumber)
                )
            }
        }
    }
}