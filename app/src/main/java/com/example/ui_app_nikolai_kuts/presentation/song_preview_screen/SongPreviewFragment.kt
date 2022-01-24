package com.example.ui_app_nikolai_kuts.presentation.song_preview_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentSongPreviewsBinding

class SongPreviewFragment : Fragment(R.layout.fragment_song_previews) {

    private val binding by viewBinding(FragmentSongPreviewsBinding::bind)
    private val songPreviewAdapter = SongPreviewAdapter(onItemClick = ::navigateToSongFragment)
    private val viewModel: ISongPreviewViewModel by activityViewModels<SongPreviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.data.observe(viewLifecycleOwner) { songPreviews ->
            songPreviewAdapter.setData(data = songPreviews)
        }

    }

    private fun navigateToSongFragment(songId: String) {
        SongPreviewFragmentDirections.actionSongPreviewFragmentToSongFragment(songId).also {
            findNavController().navigate(it)
        }
    }

    private fun initRecyclerView() {
        binding.songPreviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = songPreviewAdapter
        }
    }
}