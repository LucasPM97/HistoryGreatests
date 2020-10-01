package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter

class ChaptersViewModel: ViewModel() {

    val chapters = MutableLiveData<List<Chapter>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


    fun loadBooks(){
        loadingError.value = false
        loading.value = true
        val chapter1 = Chapter(chapter_id= "1",title = "Technology",imageUrl="https://www.imore.com/sites/imore.com/files/styles/large/public/field/image/2014/03/topic_iphone_2g.png")
        val chapter2 = Chapter(chapter_id="2",title = "States",imageUrl="https://aiconica.net/previews/institution-icon-68.png")
        val chapter3 = Chapter(chapter_id="3",title = "Me",imageUrl="https://cdn.pixabay.com/photo/2016/11/14/17/39/person-1824147_960_720.png")

        chapters.value = listOf(
            chapter1,chapter2,chapter3
        )
        loading.value = false
    }
}