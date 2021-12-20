package com.example.ui_app_nikolai_kuts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.databinding.FragmentGreenBinding

class GreenFragment : ClickableNumberedFragment(R.layout.fragment_green) {

    private val binding: FragmentGreenBinding by viewBinding(FragmentGreenBinding::bind)
    override var onFragmentClickListener: OnFragmentClickListener? = null

    companion object {
        fun getInstance(serialNumber: Int = 0): GreenFragment {
            return GreenFragment().apply {
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
        binding.greenCounterTextView.text = serialNumber.toString()

        binding.root.setOnClickListener {
            val nextSerialNumber = serialNumber + 1

            onFragmentClickListener?.onClick(nextSerialNumber)

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(GREEN_BACK_STACK_NAME)
                replace(
                    R.id.fragment_container,
                    getInstance(serialNumber = nextSerialNumber)
                )
            }
        }
    }
}