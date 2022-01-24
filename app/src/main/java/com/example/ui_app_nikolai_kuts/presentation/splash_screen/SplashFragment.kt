package com.example.ui_app_nikolai_kuts.presentation.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: ISplashViewModel by activityViewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeBarProgress()
        viewModel.startLoading()
    }

    private fun observeBarProgress() {
        viewModel.barProgress.observe(viewLifecycleOwner) { progress ->
            if (progress < SplashViewModel.MAX_PROGRESS_VALUE) {
                binding.progressBar.progress = progress
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_songPreviewFragment)
            }
        }
    }
}