package com.example.ui_app_nikolai_kuts.presentation.song_preview_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_app_nikolai_kuts.databinding.ItemSongPreviewBinding
import com.example.ui_app_nikolai_kuts.domain.common.update
import com.example.ui_app_nikolai_kuts.domain.entities.SongPreview

class SongPreviewAdapter(
    private val onItemClick: (String) -> Unit,
) : RecyclerView.Adapter<SongPreviewAdapter.SongPreviewViewHolder>() {

    private val songPreviews = mutableListOf<SongPreview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongPreviewViewHolder {
        return SongPreviewViewHolder(
            binding = ItemSongPreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongPreviewViewHolder, position: Int) {
        val songPreview = songPreviews[holder.absoluteAdapterPosition]
        holder.binding.songPreviewTitleTextView.text = songPreview.title
        holder.binding.songPreviewPerformerTextView.text = songPreview.performer
        holder.binding.songPreviewCoverUrlTextView.text = songPreview.coverUrl

        holder.binding.root.setOnClickListener { onItemClick(songPreview.id) }
    }

    override fun getItemCount(): Int = songPreviews.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: Set<SongPreview>) {
        songPreviews.update(data.toList())
        notifyDataSetChanged()
    }

    class SongPreviewViewHolder(
        val binding: ItemSongPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}