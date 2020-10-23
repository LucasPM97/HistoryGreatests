package com.lucas.historygreatests.ui.chapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentChapterItemBinding
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.extensions.loadFromUrl

class ChapterListAdapter(
    private var chapters: List<Chapter>
) : RecyclerView.Adapter<ChapterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_chapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = chapters[position]

        holder.binding.apply {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                rootView.clipToOutline = true
            }
            name.text = item.title
            description.text = item.description
            coloredBackground.setBackgroundColor(Color.parseColor(item.imageColor))
            image.loadFromUrl(item.imageUrl.toString())
            root.setOnClickListener {
                val action =
                    ChaptersFragmentDirections
                        .actionNavigationChaptersToNavigationChaptersDetailed(
                            item.chapter_id,
                            item.imageUrl,
                            item.title,
                            item.imageColor,
                            item.startYear,
                            item.endYear
                        )

                it?.findNavController()?.navigate(action)
            }
        }

    }

    override fun getItemCount(): Int = chapters.size

    fun updateList(values:List<Chapter>) {
        chapters = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = FragmentChapterItemBinding.bind(view)
    }
}
