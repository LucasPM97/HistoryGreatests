package com.lucas.historygreatests.utils.database

object FirestoreConstants {

    object Topics{
        const val COLLECTION = "topics"
        const val BOOKS_SUBCOLLECTION = "books"

        object Indexes {
            const val VIEWS = "views"
        }
    }

    object Books{
        const val COLLECTION = "books"
        const val CHAPTER_SUBCOLLECTION = "chapters"

        object Indexes {
            const val ORDER = "order"
        }
    }

    object Chapters{
        const val COLLECTION = "chapters"
    }

}