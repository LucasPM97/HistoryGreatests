package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Chapter

class ChapterDetailedViewModel : BaseViewModel() {

    val chapter = MutableLiveData<Chapter>()

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

    fun loadChapter(){
        loading.value = true
        errorLoading.value = false
        chapter.value?.let {

            chapter.value = Chapter(
                chapter_id= it.chapter_id,
                title= it.title,
                description = "",
                imageUrl = it.imageUrl,
                imageColor= it.imageColor,
                startYear= it.startYear,
                endYear= it.endYear ?: "",
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas aliquam ex eros, vel ultricies leo tempor id. Quisque quam purus, posuere sed eleifend non, iaculis non metus. Ut rhoncus, orci nec suscipit hendrerit, nisl turpis varius nulla, in elementum mi ante et lorem. Donec sodales nunc sit amet euismod auctor. Etiam sit amet orci quis ipsum aliquet suscipit. Integer accumsan rhoncus ipsum, vitae faucibus sem. Mauris cursus, ante quis efficitur sollicitudin, diam est eleifend lectus, sit amet maximus felis orci at felis. Pellentesque ut felis eget neque viverra iaculis. In auctor scelerisque urna laoreet tristique. Donec porta iaculis gravida. Phasellus elit augue, condimentum quis suscipit in, consectetur sed sem. Phasellus at metus eget diam dapibus tempus non nec justo. Aliquam eget ornare dui. Maecenas a elit egestas, interdum orci sit amet, viverra nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin finibus lorem id felis vulputate, eu semper nunc lacinia.\n" +
                        "\n" +
                        "Sed luctus nisl sit amet massa dignissim ultricies. Mauris non porta est, vitae sagittis arcu. Quisque sed tortor sit amet neque dictum volutpat. Mauris eleifend ex ut tellus porttitor, eget fermentum diam tristique. Mauris viverra pretium dui, in auctor diam tristique ut. Ut sagittis et turpis eget efficitur. In blandit lorem sit amet nunc rhoncus, a vehicula velit vestibulum. Sed eu urna in urna efficitur dapibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec nec finibus urna, id suscipit nibh. Ut congue metus vel turpis ornare vehicula. Etiam convallis mauris vel tortor tincidunt gravida.\n" +
                        "\n" +
                        "Duis nec lacus at nibh pulvinar dignissim. Vivamus imperdiet euismod massa sit amet fringilla. Suspendisse eu odio gravida, sodales nunc non, varius orci. Pellentesque id facilisis massa. Sed in fringilla massa. Quisque egestas nec ex eu ornare. Phasellus vel leo laoreet, hendrerit risus vitae, ullamcorper lacus. Pellentesque mattis ultricies pharetra. Phasellus feugiat condimentum mauris, sit amet venenatis tellus laoreet nec. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aenean eu erat nec arcu hendrerit gravida. Morbi eget tortor pretium sem suscipit accumsan.\n" +
                        "\n" +
                        "Maecenas sem massa, scelerisque ut consequat eu, aliquam non ipsum. Quisque augue ante, feugiat nec ultrices et, aliquet in ante. Nulla ultricies mauris at ex consequat laoreet. Fusce tempus leo ut orci lobortis, dapibus sagittis enim convallis. Praesent laoreet malesuada dolor, eget imperdiet purus dignissim vel. Vivamus pellentesque ex nec libero malesuada, nec rhoncus est mollis. Quisque id eros ac risus malesuada mollis.\n" +
                        "\n" +
                        "Sed sit amet lectus nibh. Proin sed maximus mi. Sed nulla eros, vulputate et pretium vitae, tempus in ante. Aenean bibendum odio diam. Phasellus rhoncus arcu id ex egestas imperdiet. Duis vulputate dui non odio semper faucibus. In venenatis ipsum nunc, eleifend iaculis risus fringilla mattis. Morbi sagittis ex odio, et sagittis nisl rutrum vitae. Praesent est lorem, interdum at massa vitae, bibendum euismod ex. Nulla molestie nibh mattis arcu pulvinar tincidunt. Phasellus pretium magna ut libero blandit, at convallis turpis sollicitudin."
            )
        }

        //errorLoading.value = true

        loading.value = false
    }

}