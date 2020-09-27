package com.lucas.historygreatests.ui.topics

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucas.historygreatests.R
import com.lucas.historygreatests.models.Topic

import kotlinx.android.synthetic.main.fragment_topic_item.view.*

class TopicListAdapter(
    private val topics: List<Topic>
) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_topic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topics[position]
        holder.itemView.name.text = item.name
        //holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = topics.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}