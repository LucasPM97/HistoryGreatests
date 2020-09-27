package com.lucas.historygreatests.ui.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lucas.historygreatests.R
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.loadFromUrl
import kotlinx.android.synthetic.main.fragment_topic_item.view.*

class TopicListAdapter(
    private var topics: List<Topic>
) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_topic_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topics[position]
        holder.itemView.name.text = item.name
        holder.itemView.image.loadFromUrl(item.imageUrl.toString())
        //holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = topics.size

    fun updateList(values:List<Topic>) {
        topics = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}