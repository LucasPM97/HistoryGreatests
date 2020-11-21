package com.lucas.historygreatests.repositories.services

object FirestoreConstants {

    object Topics {
        const val COLLECTION = "topics"
        const val BOOKS_SUBCOLLECTION = "books"

        object Indexes {
            const val VIEWS = "views"
        }
    }

    object Books {
        const val COLLECTION = "books"
        const val CHAPTER_SUBCOLLECTION = "chapters"

        object Indexes {
            const val CHAPTER_SUBCOLLECTION_ORDER = "order"
        }
    }

    object Chapters {
        const val COLLECTION = "chapters"
    }

}