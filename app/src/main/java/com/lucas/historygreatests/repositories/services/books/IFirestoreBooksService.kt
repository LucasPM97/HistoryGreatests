package com.lucas.historygreatests.repositories.services.books

import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.repositories.services.FirestorePaginationQueryCallback

interface IFirestoreBooksService {

    fun getBooksByTopicId(topicId:String, lastDocumentSnapshot: DocumentSnapshot?, callback: FirestorePaginationQueryCallback<Book>)

}