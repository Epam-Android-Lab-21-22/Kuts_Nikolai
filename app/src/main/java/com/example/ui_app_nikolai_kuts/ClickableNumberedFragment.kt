package com.example.ui_app_nikolai_kuts

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class ClickableNumberedFragment : Fragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected companion object {
        const val SERIAL_NUMBER_KEY = "serial_number"
    }

    val serialNumber: Int
        get() = arguments?.getInt(SERIAL_NUMBER_KEY) ?: 0

    protected abstract var onFragmentClickListener: OnFragmentClickListener?
}