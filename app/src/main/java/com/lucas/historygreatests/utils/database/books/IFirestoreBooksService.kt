package com.lucas.historygreatests.utils.database.books

import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback

interface IFirestoreBooksService {

    fun getBooksByTopicId(topicId:String, lastDocumentSnapshot: DocumentSnapshot?, callback: FirestorePaginationQueryCallback<Book>)

}