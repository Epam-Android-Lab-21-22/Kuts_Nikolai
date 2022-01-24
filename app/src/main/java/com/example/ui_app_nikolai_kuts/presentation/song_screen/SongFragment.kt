package com.example.ui_app_nikolai_kuts.presentation.song_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui_app_nikolai_kuts.R
import com.example.ui_app_nikolai_kuts.databinding.FragmentSongBinding
import com.example.ui_app_nikolai_kuts.domain.entities.Song

class SongFragment : Fragment(R.layout.fragment_song) {

    private val binding by viewBinding(FragmentSongBinding::bind)
    private val args by navArgs<SongFragmentArgs>()
    private val viewModel: ISongViewModel by activityViewModels { SongViewMoodleFactory(args.songId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner) { song: Song? ->
            if (song == null) showToast(getString(R.string.song_not_found))
            else binding.setContent(song)
        }
    }

    private fun getSongViewModel(): SongViewModel = ViewModelProvider(
        this,
        SongViewMoodleFactory(args.songId)
    )[SongViewModel::class.java]

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun FragmentSongBinding.setContent(song: Song) {
        songTextTextView.text = song.lyrics
        songTitleTextView.text = song.title
        songPerformerTextView.text = song.performer
        songCoverUrlTextView.text = song.coverUrl
        songAlbumTextView.text = song.album
        songGenreTextView.text = song.genre
        songYearTextView.text = song.year.toString()
    }
}