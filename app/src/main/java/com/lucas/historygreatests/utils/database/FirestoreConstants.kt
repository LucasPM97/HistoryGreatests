package com.lucas.historygreatests.utils.database

object FirestoreConstants {

    object Topics{
        const val COLLECTION = "topics"
        const val BOOKS_SUBCOLLECTION = "books"

        object Indexes {
            const val VIEWS = "views"
        }
    }

    object Chapters{
        const val COLLECTION = "chapters"

        object QueryParams {
            const val BOOK_ID = "book_id"
        }

        object Indexes {
            const val ORDER = "order"
        }
    }

}