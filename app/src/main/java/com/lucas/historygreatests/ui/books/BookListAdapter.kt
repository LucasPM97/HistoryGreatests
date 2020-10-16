package com.lucas.historygreatests.ui.books

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lucas.historygreatests.R
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.extensions.loadFromUrl
import kotlinx.android.synthetic.main.fragment_book_item.view.*

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

        holder.itemView.apply {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                root_view.clipToOutline = true
            }
            name.text = item.name
            image.loadFromUrl(item.imageUrl.toString())

            setOnClickListener {
                val action =
                    BookFragmentDirections
                        .actionNavigationBooksToNavigationChapters(item.book_id)
                it?.findNavController()?.navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = books.size

    fun updateList(values:List<Book>) {
        books = values
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
