package com.lucas.historygreatests.ui.topics

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucas.historygreatests.R
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.loadFromUrl
import kotlinx.android.synthetic.main.fragment_topic_item.view.*

class BookListAdapter(
    private var books: List<Book>
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = books[position]
        holder.itemView.name.text = item.name
        holder.itemView.image.loadFromUrl(item.imageUrl.toString())

        holder.itemView.setOnClickListener {
            //TODO: Add navigation to Chapter list
        }
    }

    override fun getItemCount(): Int = books.size

    fun updateList(values:List<Book>) {
        books = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
