package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.Chapter

class ChapterDetailedViewModel : ViewModel() {
    fun setup(args: ChapterDetailedFragmentArgs) {
        chapter.value = Chapter(
            chapter_id= args.chapterId,
            title= args.title,
            description = "",
            imageUrl = args.imageUrl,
            imageColor= args.imageColor,
            startYear= args.startYear,
            endYear= args.endYear ?: "",
        )
    }

    val chapter = MutableLiveData<Chapter>()



}