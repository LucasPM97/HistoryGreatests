package com.lucas.historygreatests.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lucas.historygreatests.databinding.FragmentBookItemBinding
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.extensions.loadFromUrl

class BookListAdapter(
    private var books: List<Book>
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentBookItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(books[position])

    override fun getItemCount(): Int = books.size

    fun updateList(values:List<Book>) {
        books = values
        notifyDataSetChanged()
    }

    class ViewHolder(val binding:FragmentBookItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(book:Book){
            binding.apply {

                binding.book = book

                root.setOnClickListener {
                    val action =
                        BookFragmentDirections
                            .actionNavigationBooksToNavigationChapters(book.book_id)
                    it?.findNavController()?.navigate(action)
                }
            }
        }
    }
}
