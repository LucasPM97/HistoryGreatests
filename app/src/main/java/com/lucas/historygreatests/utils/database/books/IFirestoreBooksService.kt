package com.lucas.historygreatests.utils.database.books

import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback

interface IFirestoreBooksService {

    fun getBooksByTopicId(topicId:String, callback: FirestoreQueryCallback<Book>)

}