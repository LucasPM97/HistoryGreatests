package com.lucas.historygreatests.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Topic

class HomeViewModel : BaseViewModel() {

    val topics = MutableLiveData<List<Topic>>()


    fun loadTopics(){
        errorLoading.value = false
        loading.value = true
        val topic1 = Topic(topic_id="1",name = "Technology",imageUrl="https://www.imore.com/sites/imore.com/files/styles/large/public/field/image/2014/03/topic_iphone_2g.png")
        val topic2 = Topic(topic_id="2",name = "States",imageUrl="https://aiconica.net/previews/institution-icon-68.png")
        val topic3 = Topic(topic_id="3",name = "Me",imageUrl="https://cdn.pixabay.com/photo/2016/11/14/17/39/person-1824147_960_720.png")

        topics.value = listOf(
            topic1,topic2,topic3
        )
        loading.value = false
    }
}