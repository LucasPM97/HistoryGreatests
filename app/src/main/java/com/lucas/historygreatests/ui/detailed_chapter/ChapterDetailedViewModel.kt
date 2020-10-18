package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.chapters.FirestoreChaptersService

class ChapterDetailedViewModel : BaseViewModel() {

    val chapter = MutableLiveData<Chapter>()
    private val firestoreService = FirestoreChaptersService()

    fun setup(args: ChapterDetailedFragmentArgs) {
        args.apply {
            chapter.value = Chapter(
                chapter_id= chapterId,
                title= title,
                description = "",
                imageUrl = imageUrl,
                imageColor= imageColor,
                startYear= startYear,
                endYear= endYear ?: "",
            )
        }

    }

    fun loadChapter(chapterId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getDetailedChapterById(chapterId,object : FirestoreCallback<Chapter> {
            override fun onSuccess(result: Chapter?) {
                chapter.value = result
            }

            override fun onFailed(exception: Exception) {
                errorLoading.value = true
            }

            override fun onCompleted() {
                loading.value = false
            }

        })
    }

}