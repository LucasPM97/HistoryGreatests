package com.lucas.historygreatests.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lucas.historygreatests.databinding.FragmentTopicItemBinding
import com.lucas.historygreatests.models.Topic

class TopicListAdapter(
    private var topics: List<Topic>
) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentTopicItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(topics[position])

    override fun getItemCount(): Int = topics.size

    fun updateList(values:List<Topic>) {
        topics = values
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: FragmentTopicItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(topic: Topic){
            binding.apply {

                binding.topic = topic

                root.setOnClickListener {
                    val action = HomeFragmentDirections.actionNavigationHomeToNavigationBooks(topic.topic_id)

                    it?.findNavController()?.navigate(action)
                }
            }
        }
    }
}
