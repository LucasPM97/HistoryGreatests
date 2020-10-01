package com.lucas.historygreatests.ui.topics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Topic

class BooksViewModel: ViewModel() {

    val books = MutableLiveData<List<Book>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


    fun loadBooks(){
        loadingError.value = false
        loading.value = true
        val book1 = Book(book_id= "1",name = "Technology",imageUrl="https://www.imore.com/sites/imore.com/files/styles/large/public/field/image/2014/03/topic_iphone_2g.png")
        val book2 = Book(book_id="2",name = "States",imageUrl="https://aiconica.net/previews/institution-icon-68.png")
        val book3 = Book(book_id="3",name = "Me",imageUrl="https://cdn.pixabay.com/photo/2016/11/14/17/39/person-1824147_960_720.png")

        books.value = listOf(
            book1,book2,book3
        )
        loading.value = false
    }
}