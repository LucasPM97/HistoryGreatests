package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ChaptersViewModel: ViewModel() {

    val chapters = MutableLiveData<List<Chapter>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


    fun loadBooks(){
        loadingError.value = false
        loading.value = true

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val chapter1 = Chapter(
                chapter_id = "1",
                title = "The begin",
                description = "Why a new phone if Blackberry already controls the market?",
                imageUrl = "https://i.pinimg.com/originals/3f/26/ed/3f26ed299460e9937d945d55f1c595a8.jpg",
                imageColor = "#FCE3BA",
                startYear = "2020",
                endYear ="2020"

            )
            val chapter2 = Chapter(
                chapter_id = "2",
                title = "It's time to shine",
                description = "Why a new phone if Blackberry already controls the market?",
                imageUrl = "https://www.geeky-gadgets.com/wp-content/uploads/2020/01/first-apple-iPhone.jpg",
                imageColor = "#CBCBC9",
                startYear = "2020",
                endYear = "2020"
            )
            val chapter3 = Chapter(
                chapter_id = "3",
                title = "Steve Jobs debuts the iPhone",
                description = "Why a new phone if Blackberry already controls the market?",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg",
                imageColor = "#0E0A2E",
                startYear = "2020",
                endYear = "2020"
            )

            chapters.value = listOf(
                chapter1, chapter2, chapter3
            )

        }
        loading.value = false
    }
}