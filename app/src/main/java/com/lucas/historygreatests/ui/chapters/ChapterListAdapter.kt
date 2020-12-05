package com.lucas.historygreatests.ui.chapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.lucas.historygreatests.databinding.FragmentChapterItemBinding
import com.lucas.historygreatests.models.Chapter

class ChapterListAdapter(
    private var chapters: List<Chapter>
) : RecyclerView.Adapter<ChapterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentChapterItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(chapters[position])

    override fun getItemCount(): Int = chapters.size

    fun updateList(values: List<Chapter>) {
        chapters = values
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: FragmentChapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chapter: Chapter) {
            binding.apply {
                binding.chapter = chapter
                root.setOnClickListener {
                    onClick(chapter)
                }
            }
        }

        private fun onClick(chapter: Chapter) {
            val action = ChaptersFragmentDirections
                .actionNavigationChaptersToNavigationChaptersDetailed(
                    chapter.chapter_id,
                    chapter.imageUrl,
                    chapter.title,
                    chapter.imageColor,
                    chapter.startYear,
                    chapter.endYear

                );

            binding.root.findNavController().navigate(action)
        }
    }
}
