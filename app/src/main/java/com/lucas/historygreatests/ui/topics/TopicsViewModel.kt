package com.lucas.historygreatests.ui.topics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.Topic

class TopicsViewModel: ViewModel() {

    val topics = MutableLiveData<List<Topic>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


    fun loadTopics(){
        loadingError.value = false
        loading.value = true
        val topic1 = Topic(topic_id="1",name = "Technology",imageUrl="")
        val topic2 = Topic(topic_id="2",name = "States",imageUrl="")
        val topic3 = Topic(topic_id="3",name = "Me",imageUrl="")

        topics.value = listOf(
           topic1,topic2,topic3
        )
        loading.value = false
    }
}