package com.lucas.historygreatests.ui.detailed_chapter

import android.app.Application
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lucas.historygreatests.database.ChaptersRoomDatabase
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.repositories.DetailedChapterRepository
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import kotlinx.coroutines.launch

class ChapterDetailedViewModel(application: Application) : BaseViewModel(application),
    IChapterDetailedViewModel {


    override val repository = DetailedChapterRepository(
        ChaptersRoomDatabase.getDatabase(context).chaptersDao()
    )

    override fun chapterDetails(chapterId: String) =
        repository.getChapterById(chapterId).asLiveData()


    override fun loadChapter(chapterId: String) {
        errorLoading.value = false
        loading.value = true

        viewModelScope.launch {
            repository.loadChapterDataFromRemote(chapterId,
                object : FirestoreDocumentCallback<Chapter> {
                    override fun onSuccess(result: Chapter?) {
                        if (result != null)
                            storeChapterUpdates(result)
                        else
                            errorLoading.value = true

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

    override fun storeChapterUpdates(chapter: Chapter) = viewModelScope.launch {
        repository.updateChapter(chapter)
    }
}