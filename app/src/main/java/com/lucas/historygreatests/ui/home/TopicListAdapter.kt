package com.lucas.historygreatests.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentTopicItemBinding
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.extensions.loadFromUrl

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

        holder.apply {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                binding.rootView.clipToOutline = true
            }
            binding.name.text = item.name
            binding.image.loadFromUrl(item.imageUrl.toString())

            binding.root.setOnClickListener {

                val action = HomeFragmentDirections.actionNavigationHomeToNavigationBooks(item.topic_id)
                it?.findNavController()?.navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = topics.size

    fun updateList(values:List<Topic>) {
        topics = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = FragmentTopicItemBinding.bind(view)
    }
}
