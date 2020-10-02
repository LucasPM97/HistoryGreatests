package com.lucas.historygreatests.ui.chapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucas.historygreatests.R
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.loadFromUrl
import kotlinx.android.synthetic.main.fragment_chapter_item.view.*
import kotlinx.android.synthetic.main.fragment_chapter_item.view.root_view
import kotlinx.android.synthetic.main.fragment_topic_item.view.image
import kotlinx.android.synthetic.main.fragment_topic_item.view.name

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
        holder.itemView.name.text = item.title
        holder.itemView.image.loadFromUrl(item.imageUrl.toString())

        holder.itemView.setOnClickListener {
            //TODO: Add navigation to Chapter details
        }
    }

    override fun getItemCount(): Int = chapters.size

    fun updateList(values:List<Chapter>) {
        chapters = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
